// Copyright 2018, OpenCensus Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package io.orijtech.integrations.ocjedis;

import io.opencensus.common.Scope;
import io.opencensus.stats.Aggregation;
import io.opencensus.stats.BucketBoundaries;
import io.opencensus.stats.Measure.MeasureDouble;
import io.opencensus.stats.Measure.MeasureLong;
import io.opencensus.stats.MeasureMap;
import io.opencensus.stats.Stats;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.stats.View;
import io.opencensus.stats.View.Name;
import io.opencensus.stats.ViewManager;
import io.opencensus.tags.TagContextBuilder;
import io.opencensus.tags.TagKey;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import io.opencensus.tags.Tags;
import io.opencensus.trace.Span;
import io.opencensus.trace.Status;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import java.util.Arrays;
import javax.annotation.Nullable;

public class Observability {

  private Observability() {}

  private static final StatsRecorder statsRecorder = Stats.getStatsRecorder();
  private static final Tagger tagger = Tags.getTagger();
  private static final Tracer tracer = Tracing.getTracer();

  // Units of measurement
  private static final String MILLISECONDS = "ms";
  private static final String BYTES = "By";

  // Tag keys
  static final TagKey METHOD = TagKey.create("method");
  static final TagKey ERROR = TagKey.create("error");
  static final TagKey STATUS = TagKey.create("status");
  static final TagKey DIRECTION = TagKey.create("type"); // DIRECTION be either of "read" or "write"

  // Tag values
  static final TagValue VALUE_OK = TagValue.create("OK");
  static final TagValue VALUE_ERROR = TagValue.create("ERROR");

  // Measures
  static final MeasureDouble MEASURE_LATENCY_MS =
      MeasureDouble.create("jedis/latency", "The latency of the various calls", MILLISECONDS);

  static final MeasureLong MEASURE_DATA_TRANSFERRED =
      MeasureLong.create("jedis/data_transferred", "Measures the data transferred", BYTES);

  // Visible for testing.
  static final Aggregation DEFAULT_MILLISECONDS_DISTRIBUTION =
      Aggregation.Distribution.create(
          BucketBoundaries.create(
              Arrays.asList(
                  // [0ms, 0.001ms, 0.005ms, 0.01ms, 0.05ms, 0.1ms, 0.5ms, 1ms, 1.5ms, 2ms, 2.5ms,
                  // 5ms, 10ms, 25ms, 50ms, 100ms, 200ms, 400ms, 600ms, 800ms, 1s, 1.5s, 2s, 2.5s,
                  // 5s, 10s, 20s, 40s, 100s, 200s, 500s]
                  0.0,
                  0.001,
                  0.005,
                  0.01,
                  0.05,
                  0.1,
                  0.5,
                  1.0,
                  1.5,
                  2.0,
                  2.5,
                  5.0,
                  10.0,
                  25.0,
                  50.0,
                  100.0,
                  200.0,
                  400.0,
                  600.0,
                  800.0,
                  1000.0,
                  1500.0,
                  2000.0,
                  2500.0,
                  5000.0,
                  10000.0,
                  20000.0,
                  40000.0,
                  100000.0,
                  200000.0,
                  500000.0)));

  static final Aggregation DEFAULT_BYTES_DISTRIBUTION =
      Aggregation.Distribution.create(
          BucketBoundaries.create(
              Arrays.asList(
                  // [0B, 1KB, 2KB, 4KB, 16KB, 64KB, 256KB, 1MB, 4MB, 16MB, 64MB, 256MB, 1GB, 4GB]
                  0.0,
                  1024.0,
                  2048.0,
                  4096.0,
                  16384.0,
                  65536.0,
                  262144.0,
                  1048576.0,
                  4194304.0,
                  16777216.0,
                  67108864.0,
                  268435456.0,
                  1073741824.0,
                  4294967296.0)));

  static final Aggregation COUNT = Aggregation.Count.create();

  // And the for views
  static final View LATENCY_VIEW =
      View.create(
          Name.create("jedis/latency"),
          "The distribution of the various latencies of the various Jedis methods",
          MEASURE_LATENCY_MS,
          DEFAULT_MILLISECONDS_DISTRIBUTION,
          Arrays.asList(METHOD, ERROR, STATUS));

  static final View CALLS_VIEW =
      View.create(
          Name.create("jedis/calls"),
          "The calls made by the various Jedis methods",
          MEASURE_LATENCY_MS,
          COUNT,
          Arrays.asList(METHOD, ERROR, STATUS));

  static final View DATA_TRANSFER_VIEW =
      View.create(
          Name.create("jedis/data_transferred"),
          "The amount of data transferred",
          MEASURE_DATA_TRANSFERRED,
          DEFAULT_BYTES_DISTRIBUTION,
          Arrays.asList(METHOD, DIRECTION));

  public static void registerAllViews() {
    registerAllViews(Stats.getViewManager());
  }

  static void registerAllViews(ViewManager manager) {
    for (View v : Arrays.asList(LATENCY_VIEW, CALLS_VIEW, DATA_TRANSFER_VIEW)) {
      manager.registerView(v);
    }
  }

  // TrackingOperation records both the metric latency in milliseconds, and the span created by
  // tracing the calling function.
  static final class TrackingOperation {
    private final Span span;
    private final long startTimeNs;
    private final String method;
    private final String key;
    private boolean closed;
    private String recordedError;

    private final StatsRecorder statsRecorder;
    private final Tagger tagger;
    private final Tracer tracer;

    TrackingOperation(String method) {
      this(method, null, Observability.statsRecorder, Observability.tagger, Observability.tracer);
    }

    TrackingOperation(String method, @Nullable String key) {
      this(method, key, Observability.statsRecorder, Observability.tagger, Observability.tracer);
    }

    // VisibleForTesting
    TrackingOperation(
        String method, String key, StatsRecorder statsRecorder, Tagger tagger, Tracer tracer) {
      startTimeNs = System.nanoTime();
      span = tracer.spanBuilder(method).startSpan();
      this.method = method;
      this.key = key;
      this.statsRecorder = statsRecorder;
      this.tagger = tagger;
      this.tracer = tracer;
    }

    @SuppressWarnings("MustBeClosedChecker")
    Scope withSpan() {
      return tracer.withSpan(span);
    }

    void end() {
      if (closed) return;

      try {
        // Finally record the latency of the entire call,
        // as well as "status": "OK" for non-error calls.
        TagContextBuilder tagContextBuilder = tagger.currentBuilder();
        tagContextBuilder.put(METHOD, TagValue.create(this.method));

        if (recordedError == null) {
          tagContextBuilder.put(STATUS, VALUE_OK);
        } else {
          tagContextBuilder.put(ERROR, TagValue.create(recordedError));
          tagContextBuilder.put(STATUS, VALUE_ERROR);
        }

        long totalTimeNs = System.nanoTime() - this.startTimeNs;

        // Create the measure map that we'll record the various metrics in.
        MeasureMap measureMap = statsRecorder.newMeasureMap();

        // Record the key length if applicable.
        if (key != null) {
          measureMap.put(Observability.MEASURE_DATA_TRANSFERRED, key.length());
        }

        // Record the latency.
        double timeSpentMs = ((double) totalTimeNs) / 1e6;
        measureMap.put(Observability.MEASURE_LATENCY_MS, timeSpentMs);

        // Now finally record all the stats the same tags.
        measureMap.record(tagContextBuilder.build());
      } finally {
        span.end();
        closed = true;
      }
    }

    // Annotates the underlying span with the description of the exception. The actual ending
    // will be performed by end.
    void recordException(Exception e) {
      recordedError = e.toString();
      span.setStatus(Status.UNKNOWN.withDescription(recordedError));
    }
  }

  static TrackingOperation createRoundtripTrackingSpan(String method) {
    return new TrackingOperation(method);
  }

  static TrackingOperation createRoundtripTrackingSpan(String method, String key) {
    return new TrackingOperation(method, key);
  }
}

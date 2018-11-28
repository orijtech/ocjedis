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

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import io.opencensus.stats.Aggregation.Distribution;
import io.opencensus.stats.BucketBoundaries;
import io.opencensus.stats.Measure.MeasureDouble;
import io.opencensus.stats.Measure.MeasureLong;
import io.opencensus.stats.MeasureMap;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.stats.View;
import io.opencensus.stats.ViewManager;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagContextBuilder;
import io.opencensus.tags.TagKey;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanBuilder;
import io.opencensus.trace.Status;
import io.opencensus.trace.Tracer;
import io.orijtech.integrations.ocjedis.Observability.TrackingOperation;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/** Tests for {@link Observability}. */
@RunWith(JUnit4.class)
public class ObservabilityTest {

  @Mock private ViewManager mockViewManager;
  @Mock private Tagger mockTagger;
  @Mock private StatsRecorder mockStatsRecorder;
  @Mock private Tracer mockTracer;

  @Mock private MeasureMap mockMeasureMap;
  @Mock private TagContextBuilder mockTagContextBuilder;
  @Mock private TagContext mockTagContext;
  @Mock private Span mockSpan;
  @Mock private SpanBuilder mockSpanBuilder;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Mockito.doNothing().when(mockViewManager).registerView(any(View.class));
    Mockito.when(mockTagger.currentBuilder()).thenReturn(mockTagContextBuilder);
    Mockito.when(mockTagContextBuilder.put(any(TagKey.class), any(TagValue.class)))
        .thenReturn(mockTagContextBuilder);
    Mockito.when(mockTagContextBuilder.build()).thenReturn(mockTagContext);
    Mockito.when(mockStatsRecorder.newMeasureMap()).thenReturn(mockMeasureMap);
    Mockito.when(mockMeasureMap.put(any(MeasureDouble.class), anyDouble()))
        .thenReturn(mockMeasureMap);
    Mockito.doNothing().when(mockMeasureMap).record(any(TagContext.class));
    Mockito.when(mockTracer.spanBuilderWithExplicitParent(anyString(), anyObject()))
        .thenReturn(mockSpanBuilder);
    Mockito.when(mockSpanBuilder.startSpan()).thenReturn(mockSpan);
    Mockito.doNothing().when(mockSpan).putAttribute(anyString(), any(AttributeValue.class));
  }

  @Test
  public void testConstants() {
    // Check the Tag keys.
    assertThat(Observability.METHOD).isEqualTo(TagKey.create("method"));
    assertThat(Observability.ERROR).isEqualTo(TagKey.create("error"));
    assertThat(Observability.STATUS).isEqualTo(TagKey.create("status"));
    assertThat(Observability.DIRECTION).isEqualTo(TagKey.create("type"));

    // Check the Tag values.
    assertThat(Observability.VALUE_OK).isEqualTo(TagValue.create("OK"));
    assertThat(Observability.VALUE_ERROR).isEqualTo(TagValue.create("ERROR"));

    // Check the Measures.
    assertThat(Observability.MEASURE_LATENCY_MS)
        .isEqualTo(MeasureDouble.create("jedis/latency", "The latency of the various calls", "ms"));
    assertThat(Observability.MEASURE_DATA_TRANSFERRED)
        .isEqualTo(
            MeasureLong.create("jedis/data_transferred", "Measures the data transferred", "By"));

    // Check the distributions
    assertThat(Observability.DEFAULT_MILLISECONDS_DISTRIBUTION)
        .isEqualTo(
            Distribution.create(
                BucketBoundaries.create(
                    Arrays.asList(
                        0.0, 0.001, 0.005, 0.01, 0.05, 0.1, 0.5, 1.0, 1.5, 2.0, 2.5, 5.0, 10.0,
                        25.0, 50.0, 100.0, 200.0, 400.0, 600.0, 800.0, 1000.0, 1500.0, 2000.0,
                        2500.0, 5000.0, 10000.0, 20000.0, 40000.0, 100000.0, 200000.0, 500000.0))));

    assertThat(Observability.DEFAULT_BYTES_DISTRIBUTION)
        .isEqualTo(
            Distribution.create(
                BucketBoundaries.create(
                    Arrays.asList(
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
                        4294967296.0))));
  }

  @Test
  public void registerAllViews() {
    Observability.registerAllViews(mockViewManager);
    Mockito.verify(mockViewManager, Mockito.times(1)).registerView(Observability.LATENCY_VIEW);
    Mockito.verify(mockViewManager, Mockito.times(1)).registerView(Observability.CALLS_VIEW);
    Mockito.verify(mockViewManager, Mockito.times(1))
        .registerView(Observability.DATA_TRANSFER_VIEW);
  }

  @Test
  public void trackingOperation_withSpan() {
    TrackingOperation trackingOperation =
        new TrackingOperation("This method", mockStatsRecorder, mockTagger, mockTracer, "akey");
    trackingOperation.withSpan();
    Mockito.verify(mockTracer, Mockito.times(1)).spanBuilderWithExplicitParent("This method", null);
    Mockito.verify(mockSpanBuilder, Mockito.times(1)).startSpan();
  }

  @Test
  public void trackingOperation_end() {
    TrackingOperation trackingOperation =
        new TrackingOperation("llen", mockStatsRecorder, mockTagger, mockTracer, "key1", "key2");
    trackingOperation.end();
    Mockito.verify(mockTagger, Mockito.times(1)).currentBuilder();
    Mockito.verify(mockTagContextBuilder, Mockito.times(1))
        .put(eq(Observability.METHOD), eq(TagValue.create("llen")));
    Mockito.verify(mockTagContextBuilder, Mockito.times(1))
        .put(eq(Observability.STATUS), eq(Observability.VALUE_OK));
    Mockito.verify(mockStatsRecorder, Mockito.times(1)).newMeasureMap();
    Mockito.verify(mockMeasureMap, Mockito.times(1))
        .put(eq(Observability.MEASURE_LATENCY_MS), anyDouble());
    Mockito.verify(mockMeasureMap, Mockito.times(2))
        .put(eq(Observability.MEASURE_DATA_TRANSFERRED), anyLong());
    Mockito.verify(mockMeasureMap, Mockito.times(1)).record(any(TagContext.class));
    Mockito.verify(mockSpan, Mockito.times(1)).end();
  }

  @Test
  public void trackingOperation_end_recordException() {
    TrackingOperation trackingOperation =
        new TrackingOperation("pubsub", mockStatsRecorder, mockTagger, mockTracer, "key1", "key2");
    IOException exception = new IOException("network timeout");
    trackingOperation.recordException(exception);
    trackingOperation.end();
    Mockito.verify(mockTagger, Mockito.times(1)).currentBuilder();
    Mockito.verify(mockTagContextBuilder, Mockito.times(1))
        .put(eq(Observability.METHOD), eq(TagValue.create("pubsub")));
    Mockito.verify(mockTagContextBuilder, Mockito.times(1))
        .put(eq(Observability.ERROR), eq(TagValue.create(exception.toString())));
    Mockito.verify(mockTagContextBuilder, Mockito.times(1))
        .put(eq(Observability.STATUS), eq(Observability.VALUE_ERROR));
    Mockito.verify(mockSpan, Mockito.times(1))
        .setStatus(eq(Status.UNKNOWN.withDescription(exception.toString())));
    Mockito.verify(mockStatsRecorder, Mockito.times(1)).newMeasureMap();
    Mockito.verify(mockMeasureMap, Mockito.times(1))
        .put(eq(Observability.MEASURE_LATENCY_MS), anyDouble());
    Mockito.verify(mockMeasureMap, Mockito.times(1)).record(any(TagContext.class));
    Mockito.verify(mockSpan, Mockito.times(1)).end();
  }
}

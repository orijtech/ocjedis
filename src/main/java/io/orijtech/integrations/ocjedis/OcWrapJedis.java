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
import io.orijtech.integrations.ocjedis.Observability.TrackingOperation;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.ClusterReset;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolAbstract;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;
import redis.clients.jedis.util.Slowlog;

public class OcWrapJedis extends Jedis {

  public OcWrapJedis() {
    super();
  }

  public OcWrapJedis(JedisShardInfo shardInfo) {
    super(shardInfo);
  }

  public OcWrapJedis(String host) {
    super(host);
  }

  public OcWrapJedis(String host, int port) {
    super(host, port);
  }

  public OcWrapJedis(String host, int port, boolean ssl) {
    super(host, port, ssl);
  }

  public OcWrapJedis(
      String host,
      int port,
      boolean ssl,
      SSLSocketFactory sslSocketFactory,
      SSLParameters sslParameters,
      HostnameVerifier hostnameVerifier) {
    super(host, port, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public OcWrapJedis(String host, int port, int timeout) {
    super(host, port, timeout);
  }

  public OcWrapJedis(String host, int port, int timeout, boolean ssl) {
    super(host, port, timeout, ssl);
  }

  public OcWrapJedis(
      String host,
      int port,
      int timeout,
      boolean ssl,
      SSLSocketFactory sslSocketFactory,
      SSLParameters sslParameters,
      HostnameVerifier hostnameVerifier) {
    super(host, port, timeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public OcWrapJedis(String host, int port, int connectionTimeout, int soTimeout) {
    super(host, port, connectionTimeout, soTimeout);
  }

  public OcWrapJedis(String host, int port, int connectionTimeout, int soTimeout, boolean ssl) {
    super(host, port, connectionTimeout, soTimeout, ssl);
  }

  public OcWrapJedis(
      String host,
      int port,
      int connectionTimeout,
      int soTimeout,
      boolean ssl,
      SSLSocketFactory sslSocketFactory,
      SSLParameters sslParameters,
      HostnameVerifier hostnameVerifier) {
    super(
        host,
        port,
        connectionTimeout,
        soTimeout,
        ssl,
        sslSocketFactory,
        sslParameters,
        hostnameVerifier);
  }

  public OcWrapJedis(URI uri) {
    super(uri);
  }

  public OcWrapJedis(URI uri, int timeout) {
    super(uri, timeout);
  }

  public OcWrapJedis(URI uri, int connectionTimeout, int soTimeout) {
    super(uri, connectionTimeout, soTimeout);
  }

  public OcWrapJedis(
      URI uri,
      int connectionTimeout,
      int soTimeout,
      SSLSocketFactory sslSocketFactory,
      SSLParameters sslParameters,
      HostnameVerifier hostnameVerifier) {
    super(uri, connectionTimeout, soTimeout, sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public OcWrapJedis(
      URI uri,
      int timeout,
      SSLSocketFactory sslSocketFactory,
      SSLParameters sslParameters,
      HostnameVerifier hostnameVerifier) {
    super(uri, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public OcWrapJedis(
      URI uri,
      SSLSocketFactory sslSocketFactory,
      SSLParameters sslParameters,
      HostnameVerifier hostnameVerifier) {
    super(uri, sslSocketFactory, sslParameters, hostnameVerifier);
  }

  @Override
  public Long append(String key, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#append-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.append", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.append(key, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long bitcount(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#bitcount-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.bitcount", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.bitcount(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long bitcount(String key, long start, long end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#bitcount-java.lang.String-long-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.bitcount", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.bitcount(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<Long> bitfield(String key, String... arguments) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#bitfield-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.bitfield", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.bitfield(key, arguments);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long bitop(BitOP op, String destKey, String... srcKeys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#bitop-redis.clients.jedis.BitOP-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.bitop", srcKeys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.bitop(op, destKey, srcKeys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long bitpos(String key, boolean value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#bitpos-java.lang.String-boolean-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.bitpos", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.bitpos(key, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long bitpos(String key, boolean value, BitPosParams params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#bitpos-java.lang.String-boolean-redis.clients.jedis.BitPosParams-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.bitpos", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.bitpos(key, value, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> blpop(int timeout, String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#blpop-int-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.blpop", keys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.blpop(timeout, keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> blpop(int timeout, String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#blpop-int-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.blpop", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.blpop(timeout, key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> blpop(String... args) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#blpop-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.blpop");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.blpop(args);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> brpop(int timeout, String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#brpop-int-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.brpop", keys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.brpop(timeout, keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> brpop(int timeout, String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#brpop-int-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.brpop", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.brpop(timeout, key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> brpop(String... args) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#brpop-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.brpop");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.brpop(args);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String brpoplpush(String source, String destination, int timeout) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#brpoplpush-java.lang.String-java.lang.String-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.brpoplpush");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.brpoplpush(source, destination, timeout);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clientKill(String client) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clientKill-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clientKill");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clientKill(client);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clientSetname(String name) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clientSetname-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clientSetname");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clientSetname(name);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public void close() {
    // This method makes a call over the network or at least we need to track closes.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#close--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.close");

    try (Scope ws = trackingOperation.withSpan()) {
      super.close();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterAddSlots(int... slots) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterAddSlots-int...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterAddSlots");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterAddSlots(slots);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long clusterCountKeysInSlot(int slot) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterCountKeysInSlot-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.clusterCountKeysInSlot");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterCountKeysInSlot(slot);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterDelSlots(int... slots) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterDelSlots-int...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterDelSlots");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterDelSlots(slots);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterFailover() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterFailover--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterFailover");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterFailover();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterFlushSlots() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterFlushSlots--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterFlushSlots");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterFlushSlots();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterForget(String nodeId) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterForget-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterForget");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterForget(nodeId);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> clusterGetKeysInSlot(int slot, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterGetKeysInSlot-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterGetKeysInSlot");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterGetKeysInSlot(slot, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterInfo() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterInfo--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterInfo");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterInfo();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long clusterKeySlot(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterKeySlot-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterKeySlot", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterKeySlot(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterMeet(String ip, int port) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterMeet-java.lang.String-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterMeet");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterMeet(ip, port);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterNodes() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterNodes--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterNodes");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterNodes();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterReplicate(String nodeId) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterReplicate-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterReplicate");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterReplicate(nodeId);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterReset(ClusterReset resetType) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterReset-redis.clients.jedis.ClusterReset-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterReset");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterReset(resetType);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterSaveConfig() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterSaveConfig--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterSaveConfig");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterSaveConfig();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterSetSlotImporting(int slot, String nodeId) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterSetSlotImporting-int-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.clusterSetSlotImporting");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterSetSlotImporting(slot, nodeId);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterSetSlotMigrating(int slot, String nodeId) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterSetSlotMigrating-int-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.clusterSetSlotMigrating");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterSetSlotMigrating(slot, nodeId);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterSetSlotNode(int slot, String nodeId) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterSetSlotNode-int-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterSetSlotNode");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterSetSlotNode(slot, nodeId);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String clusterSetSlotStable(int slot) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterSetSlotStable-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterSetSlotStable");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterSetSlotStable(slot);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> clusterSlaves(String nodeId) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterSlaves-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterSlaves");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterSlaves(nodeId);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<Object> clusterSlots() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#clusterSlots--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.clusterSlots");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.clusterSlots();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> configGet(String pattern) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#configGet-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.configGet");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.configGet(pattern);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String configSet(String parameter, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#configSet-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.configSet");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.configSet(parameter, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long decr(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#decr-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.decr");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.decr(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long decrBy(String key, long integer) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#decrBy-java.lang.String-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.decrBy");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.decrBy(key, integer);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long del(String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#decrBy-java.lang.String-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.del", keys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.del(keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long del(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#del-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.del");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.del(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public byte[] dump(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#dump-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.dump", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.dump(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String echo(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#echo-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.echo", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.echo(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Object eval(String script) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#eval-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.eval", script);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.eval(script);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Object eval(String script, int keyCount, String... params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#eval-java.lang.String-int-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.eval", script);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.eval(script, keyCount, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Object eval(String script, List<String> keys, List<String> args) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#eval-java.lang.String-java.util.List-java.util.List-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.eval", script);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.eval(script, keys, args);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Object evalsha(String sha1) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#evalsha-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.evalsha", sha1);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.evalsha(sha1);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Object evalsha(String sha1, int keyCount, String... params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#evalsha-java.lang.String-int-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.evalsha", sha1);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.evalsha(sha1, keyCount, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Object evalsha(String sha1, List<String> keys, List<String> args) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#evalsha-java.lang.String-java.util.List-java.util.List-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.evalsha", sha1);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.evalsha(sha1, keys, args);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long exists(String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#exists-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.exists", keys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.exists(keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Boolean exists(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#exists-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.exists", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.exists(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long expire(String key, int seconds) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#expire-java.lang.String-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.expire", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.expire(key, seconds);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long expireAt(String key, long unixTime) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#expireAt-java.lang.String-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.expireAt", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.expireAt(key, unixTime);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long geoadd(String key, double longitude, double latitude, String member) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#geoadd-java.lang.String-double-double-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.geoadd", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.geoadd(key, longitude, latitude, member);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#geoadd-java.lang.String-double-double-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.geoadd", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.geoadd(key, memberCoordinateMap);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Double geodist(String key, String member1, String member2) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#geodist-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.geodist", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.geodist(key, member1, member2);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Double geodist(String key, String member1, String member2, GeoUnit unit) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#geodist-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.geodist", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.geodist(key, member1, member2, unit);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> geohash(String key, String... members) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#geohash-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.geohash", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.geohash(key, members);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<GeoCoordinate> geopos(String key, String... members) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#geopos-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.geopos", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.geopos(key, members);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<GeoRadiusResponse> georadius(
      String key, double longitude, double latitude, double radius, GeoUnit unit) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#georadius-java.lang.String-double-double-double-redis.clients.jedis.GeoUnit-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.georadius", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.georadius(key, longitude, latitude, radius, unit);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<GeoRadiusResponse> georadius(
      String key,
      double longitude,
      double latitude,
      double radius,
      GeoUnit unit,
      GeoRadiusParam param) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#georadius-java.lang.String-double-double-double-redis.clients.jedis.GeoUnit-redis.clients.jedis.params.geo.GeoRadiusParam-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.georadius", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.georadius(key, longitude, latitude, radius, unit, param);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<GeoRadiusResponse> georadiusByMember(
      String key, String member, double radius, GeoUnit unit) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#georadiusByMember-java.lang.String-java.lang.String-double-redis.clients.jedis.GeoUnit-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.georadiusByMember", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.georadiusByMember(key, member, radius, unit);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<GeoRadiusResponse> georadiusByMember(
      String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#georadiusByMember-java.lang.String-java.lang.String-double-redis.clients.jedis.GeoUnit-redis.clients.jedis.params.geo.GeoRadiusParam-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.georadiusByMember", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.georadiusByMember(key, member, radius, unit, param);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String get(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#get-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.get", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.get(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Boolean getbit(String key, long offset) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#getbit-java.lang.String-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.getbit", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.getbit(key, offset);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String getrange(String key, long startOffset, long endOffset) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#getrange-java.lang.String-long-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.getrange", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.getrange(key, startOffset, endOffset);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String getSet(String key, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#getSet-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.getSet", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.getSet(key, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long hdel(String key, String... fields) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hdel-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hdel", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hdel(key, fields);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Boolean hexists(String key, String field) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hexists-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hexists", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hexists(key, field);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String hget(String key, String field) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hget-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hget", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hget(key, field);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Map<String, String> hgetAll(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hgetAll-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hgetAll", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hgetAll(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long hincrBy(String key, String field, long value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hincrBy-java.lang.String-java.lang.String-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hincrBy", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hincrBy(key, field, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Double hincrByFloat(String key, String field, double value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hincrByFloat-java.lang.String-java.lang.String-double-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hincrByFloat", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hincrByFloat(key, field, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> hkeys(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hkeys-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hkeys", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hkeys(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long hlen(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hlen-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hlen", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hlen(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> hmget(String key, String... fields) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hmget-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hmget", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hmget(key, fields);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hscan-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hscan", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hscan(key, cursor);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hscan-java.lang.String-java.lang.String-redis.clients.jedis.ScanParams-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hscan", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hscan(key, cursor, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long hset(String key, String field, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hset-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hset", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hset(key, field, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long hsetnx(String key, String field, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hsetnx-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hsetnx", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hsetnx(key, field, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> hvals(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#hvals-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.hvals", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.hvals(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long incr(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#incr-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.incr", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.incr(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long incrBy(String key, long integer) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#incrBy-java.lang.String-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.incrBy", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.incrBy(key, integer);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Double incrByFloat(String key, double value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#incrByFloat-java.lang.String-double-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.incrByFloat", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.incrByFloat(key, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> keys(String pattern) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#keys-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.keys", pattern);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.keys(pattern);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String lindex(String key, long index) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#lindex-java.lang.String-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.lindex", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.lindex(key, index);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long linsert(String key, ListPosition where, String pivot, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#linsert-java.lang.String-redis.clients.jedis.ListPosition-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.linsert", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.linsert(key, where, pivot, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long llen(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#llen-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.llen", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.llen(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String lpop(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#lpop-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.lpop", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.lpop(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long lpush(String key, String... strings) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#lpush-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.lpush", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.lpush(key, strings);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long lpushx(String key, String... strings) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#lpushx-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.lpushx", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.lpushx(key, strings);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> lrange(String key, long start, long end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#lrange-java.lang.String-long-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.lrange", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.lrange(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long lrem(String key, long count, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#lrem-java.lang.String-long-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.lrem", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.lrem(key, count, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String lset(String key, long index, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#lset-java.lang.String-long-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.lset", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.lset(key, index, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String ltrim(String key, long start, long end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#ltrim-java.lang.String-long-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.ltrim", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.ltrim(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> mget(String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#mget-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.mget", keys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.mget(keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String migrate(String host, int port, String key, int destinationDb, int timeout) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#migrate-java.lang.String-int-java.lang.String-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.migrate", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.migrate(host, port, key, destinationDb, timeout);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long move(String key, int dbIndex) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#move-java.lang.String-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.move", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.move(key, dbIndex);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String mset(String... keysvalues) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#mset-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.mset", keysvalues);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.mset(keysvalues);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long msetnx(String... keysvalues) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#msetnx-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.msetnx", keysvalues);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.msetnx(keysvalues);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String objectEncoding(String string) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#objectEncoding-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.objectEncoding", string);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.objectEncoding(string);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long objectIdletime(String string) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#objectIdletime-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.objectIdletime", string);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.objectIdletime(string);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long objectRefcount(String string) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#objectRefcount-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.objectRefcount", string);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.objectRefcount(string);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long persist(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#persist-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.persist", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.persist(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long pexpire(String key, long milliseconds) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#pexpire-java.lang.String-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.pexpire", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.pexpire(key, milliseconds);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long pexpireAt(String key, long millisecondsTimestamp) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#pexpireAt-java.lang.String-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.pexpireAt", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.pexpireAt(key, millisecondsTimestamp);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long pfadd(String key, String... elements) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#pfadd-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.pfadd", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.pfadd(key, elements);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public long pfcount(String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#pfcount-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.pfcount", keys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.pfcount(keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public long pfcount(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#pfcount-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.pfcount", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.pfcount(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String pfmerge(String destkey, String... sourcekeys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#pfmerge-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.pfmerge", destkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.pfmerge(destkey, sourcekeys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String psetex(String key, long milliseconds, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#psetex-java.lang.String-long-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.psetex", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.psetex(key, milliseconds, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#psubscribe-redis.clients.jedis.JedisPubSub-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.psubscribe", patterns);

    try (Scope ws = trackingOperation.withSpan()) {
      super.psubscribe(jedisPubSub, patterns);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long pttl(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#pttl-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.pttl", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.pttl(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long publish(String channel, String message) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#publish-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.publish", channel, message);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.publish(channel, message);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> pubsubChannels(String pattern) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#pubsubChannels-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.pubsubChannels", pattern);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.pubsubChannels(pattern);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long pubsubNumPat() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#pubsubNumPat--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.pubsubNumPat");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.pubsubNumPat();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Map<String, String> pubsubNumSub(String... channels) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#pubsubNumSub-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.pubsubNumSub", channels);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.pubsubNumSub(channels);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String randomKey() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#randomKey--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.randomKey");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.randomKey();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String readonly() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#readonly--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.readonly");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.readonly();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String rename(String oldkey, String newkey) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#rename-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.rename", oldkey, newkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.rename(oldkey, newkey);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long renamenx(String oldkey, String newkey) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#renamenx-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.renamenx", oldkey, newkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.renamenx(oldkey, newkey);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String restore(String key, int ttl, byte[] serializedValue) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#restore-java.lang.String-int-byte:A-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.restore", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.restore(key, ttl, serializedValue);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String rpoplpush(String srckey, String dstkey) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#rpoplpush-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.rpoplpush", srckey, dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.rpoplpush(srckey, dstkey);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long rpush(String key, String... strings) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#rpush-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.rpush", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.rpush(key, strings);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long rpushx(String key, String... strings) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#rpushx-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.rpushx", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.rpushx(key, strings);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long sadd(String key, String... members) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sadd-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sadd", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sadd(key, members);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public ScanResult<String> scan(String cursor) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#scan-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.scan", cursor);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.scan(cursor);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public ScanResult<String> scan(String cursor, ScanParams params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#scan-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.scan", cursor);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.scan(cursor, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long scard(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#scard-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.scard", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.scard(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<Boolean> scriptExists(String... sha1s) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#scriptExists-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.scriptExists", sha1s);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.scriptExists(sha1s);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Boolean scriptExists(String sha1) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#scriptExists-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.scriptExists", sha1);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.scriptExists(sha1);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String scriptLoad(String script) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#scriptLoad-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.scriptLoad", script);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.scriptLoad(script);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> sdiff(String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sdiff-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sdiff", keys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sdiff(keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long sdiffstore(String dstkey, String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sdiffstore-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sdiffstore", dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sdiffstore(dstkey, keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String sentinelFailover(String masterName) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sentinelFailover-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.sentinelFailover", masterName);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sentinelFailover(masterName);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> sentinelGetMasterAddrByName(String masterName) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sentinelGetMasterAddrByName-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.sentinelGetMasterAddrByName", masterName);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sentinelGetMasterAddrByName(masterName);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<Map<String, String>> sentinelMasters() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sentinelMasters--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sentinelMasters");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sentinelMasters();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String sentinelMonitor(String masterName, String ip, int port, int quorum) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sentinelMonitor-java.lang.String-java.lang.String-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.sentinelMonitor", masterName);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sentinelMonitor(masterName, ip, port, quorum);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String sentinelRemove(String masterName) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sentinelRemove-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.sentinelRemove", masterName);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sentinelRemove(masterName);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long sentinelReset(String pattern) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sentinelReset-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.sentinelReset", pattern);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sentinelReset(pattern);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String sentinelSet(String masterName, Map<String, String> parameterMap) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sentinelSet-java.lang.String-java.util.Map-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.sentinelSet", masterName);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sentinelSet(masterName, parameterMap);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<Map<String, String>> sentinelSlaves(String masterName) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sentinelSlaves-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.sentinelSlaves", masterName);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sentinelSlaves(masterName);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String set(String key, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#set-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.set", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.set(key, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String set(String key, String value, SetParams params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#set-java.lang.String-java.lang.String-redis.clients.jedis.params.SetParams-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.set", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.set(key, value, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Boolean setbit(String key, long offset, boolean value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#setbit-java.lang.String-long-boolean-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.setbit", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.setbit(key, offset, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Boolean setbit(String key, long offset, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#setbit-java.lang.String-long-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.setbit", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.setbit(key, offset, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public void setDataSource(JedisPoolAbstract jedisPool) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#setDataSource-redis.clients.jedis.JedisPoolAbstract-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.setDataSource");

    try (Scope ws = trackingOperation.withSpan()) {
      super.setDataSource(jedisPool);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String setex(String key, int seconds, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#setex-java.lang.String-int-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.setex");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.setex(key, seconds, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long setnx(String key, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#setnx-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.setnx");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.setnx(key, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long setrange(String key, long offset, String value) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#setrange-java.lang.String-long-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.setrange", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.setrange(key, offset, value);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> sinter(String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sinter-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sinter", keys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sinter(keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long sinterstore(String dstkey, String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sinterstore-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sinterstore", dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sinterstore(dstkey, keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Boolean sismember(String key, String member) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sismember-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sismember", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sismember(key, member);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<Slowlog> slowlogGet() {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#slowlogGet--
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.slowlogGet");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.slowlogGet();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<Slowlog> slowlogGet(long entries) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#slowlogGet-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.slowlogGet");

    try (Scope ws = trackingOperation.withSpan()) {
      return super.slowlogGet(entries);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> smembers(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#smembers-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.smembers", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.smembers(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long smove(String srckey, String dstkey, String member) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#smove-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.smove", srckey, dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.smove(srckey, dstkey, member);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> sort(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sort-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sort", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sort(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> sort(String key, SortingParams sortingParameters) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sort-java.lang.String-redis.clients.jedis.SortingParams-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sort", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sort(key, sortingParameters);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long sort(String key, SortingParams sortingParameters, String dstkey) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sort-java.lang.String-redis.clients.jedis.SortingParams-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sort", key, dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sort(key, sortingParameters, dstkey);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long sort(String key, String dstkey) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sort-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sort", key, dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sort(key, dstkey);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String spop(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#spop-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.spop", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.spop(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> spop(String key, long count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#spop-java.lang.String-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.spop", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.spop(key, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String srandmember(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#srandmember-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.srandmember", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.srandmember(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public List<String> srandmember(String key, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#srandmember-java.lang.String-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.srandmember", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.srandmember(key, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long srem(String key, String... members) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#srem-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.srem", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.srem(key, members);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public ScanResult<String> sscan(String key, String cursor) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sscan-java.lang.String-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sscan", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sscan(key, cursor);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sscan-java.lang.String-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sscan", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sscan(key, cursor, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long strlen(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#strlen-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.strlen", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.strlen(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public void subscribe(JedisPubSub jedisPubSub, String... channels) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#subscribe-redis.clients.jedis.JedisPubSub-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.subscribe", channels);

    try (Scope ws = trackingOperation.withSpan()) {
      super.subscribe(jedisPubSub, channels);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String substr(String key, int start, int end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#substr-java.lang.String-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.substr", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.substr(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> sunion(String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sunion-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sunion", keys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sunion(keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long sunionstore(String dstkey, String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#sunionstore-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.sunionstore", dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.sunionstore(dstkey, keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long ttl(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#ttl-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.ttl", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.ttl(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String type(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#type-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.type", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.type(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public String watch(String... keys) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#watch-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.watch", keys);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.watch(keys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zadd(String key, double score, String member) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zadd-java.lang.String-double-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zadd", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zadd(key, score, member);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zadd(String key, double score, String member, ZAddParams params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zadd-java.lang.String-double-java.lang.String-redis.clients.jedis.params.sortedset.ZAddParams-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zadd", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zadd(key, score, member, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zadd(String key, Map<String, Double> scoreMembers) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zadd-java.lang.String-java.util.Map-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zadd", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zadd(key, scoreMembers);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zadd-java.lang.String-java.util.Map-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zadd", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zadd(key, scoreMembers, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zcard(String key) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zcard-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zcard", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zcard(key);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zcount(String key, double min, double max) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zcount-java.lang.String-double-double-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zcount", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zcount(key, min, max);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zcount(String key, String min, String max) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zcount-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zcount", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zcount(key, min, max);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Double zincrby(String key, double score, String member) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zincrby-java.lang.String-double-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zincrby", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zincrby(key, score, member);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Double zincrby(String key, double score, String member, ZIncrByParams params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zincrby-java.lang.String-double-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zincrby", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zincrby(key, score, member, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zinterstore(String dstkey, String... sets) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zinterstore-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zinterstore", dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zinterstore(dstkey, sets);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zinterstore(String dstkey, ZParams params, String... sets) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zinterstore-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zinterstore", dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zinterstore(dstkey, params, sets);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zlexcount(String key, String min, String max) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zlexcount-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zlexcount", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zlexcount(key, min, max);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrange(String key, long start, long end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrange-java.lang.String-long-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrange", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrange(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrangeByLex(String key, String min, String max) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrangeByLex-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrangeByLex", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrangeByLex(key, min, max);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrangeByLex-java.lang.String-java.lang.String-java.lang.String-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrangeByLex", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrangeByLex(key, min, max, offset, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrangeByScore(String key, String min, String max) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrangeByScore-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrangeByScore", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrangeByScore(key, min, max);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrangeByScore-java.lang.String-double-double-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrangeByScore", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrangeByScore(key, min, max, offset, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrangeByScoreWithScores-java.lang.String-double-double-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrangeByScoreWithScores", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrangeByScoreWithScores(key, min, max);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<Tuple> zrangeByScoreWithScores(
      String key, double min, double max, int offset, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrangeByScoreWithScores-java.lang.String-double-double-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrangeByScoreWithScores", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrangeByScoreWithScores(key, min, max, offset, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrangeByScoreWithScores-java.lang.String-java.lang.String-java.lang.String-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrangeByScoreWithScores", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrangeByScoreWithScores(key, min, max);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<Tuple> zrangeByScoreWithScores(
      String key, String min, String max, int offset, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrangeByScoreWithScores-java.lang.String-java.lang.String-java.lang.String-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrangeByScoreWithScores", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrangeByScoreWithScores(key, min, max, offset, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<Tuple> zrangeWithScores(String key, long start, long end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrangeWithScores-java.lang.String-long-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrangeWithScores", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrangeWithScores(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zrank(String key, String member) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrank-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrank", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrank(key, member);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zrem(String key, String... members) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrem-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrem", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrem(key, members);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zremrangeByLex(String key, String min, String max) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zremrangeByLex-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zremrangeByLex", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zremrangeByLex(key, min, max);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zremrangeByRank(String key, long start, long end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zremrangeByRank-java.lang.String-long-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zremrangeByRank", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zremrangeByRank(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zremrangeByScore(String key, double start, double end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zremrangeByScore-java.lang.String-double-double-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zremrangeByScore", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zremrangeByScore(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zremrangeByScore(String key, String start, String end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zremrangeByScore-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zremrangeByScore", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zremrangeByScore(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrevrange(String key, long start, long end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrange-java.lang.String-long-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrevrange", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrange(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrevrangeByLex(String key, String max, String min) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeByLex-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrevrangeByLex", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByLex(key, max, min);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeByLex-java.lang.String-java.lang.String-java.lang.String-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrevrangeByLex", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByLex(key, max, min, offset, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrevrangeByScore(String key, double max, double min) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeByScore-java.lang.String-double-double-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrevrangeByScore", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByScore(key, max, min);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeByScore-java.lang.String-double-double-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrevrangeByScore", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByScore(key, max, min, offset, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrevrangeByScore(String key, String max, String min) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeByScore-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrevrangeByScore", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByScore(key, max, min);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeByLex-java.lang.String-java.lang.String-java.lang.String-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrevrangeByScore", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByScore(key, max, min, offset, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeByScoreWithScores-java.lang.String-double-double-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrevrangeByScoreWithScores", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByScoreWithScores(key, max, min);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<Tuple> zrevrangeByScoreWithScores(
      String key, double max, double min, int offset, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeByScoreWithScores-java.lang.String-double-double-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrevrangeByScoreWithScores", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByScoreWithScores(key, max, min, offset, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeByScoreWithScores-java.lang.String-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrevrangeByScoreWithScores", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByScoreWithScores(key, max, min);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<Tuple> zrevrangeByScoreWithScores(
      String key, String max, String min, int offset, int count) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeByScoreWithScores-java.lang.String-java.lang.String-java.lang.String-int-int-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrevrangeByScoreWithScores", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByScoreWithScores(key, max, min, offset, count);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrangeWithScores-java.lang.String-long-long-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "redis.clients.jedis.Jedis.zrevrangeWithScores", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrangeByScoreWithScores(key, start, end);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zrevrank(String key, String member) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zrevrank-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zrevrank", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zrevrank(key, member);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public ScanResult<Tuple> zscan(String key, String cursor) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zscan-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zscan", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zscan(key, cursor);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zscan-java.lang.String-java.lang.String-redis.clients.jedis.ScanParams-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zscan", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zscan(key, cursor, params);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Double zscore(String key, String member) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zscore-java.lang.String-java.lang.String-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zscore", key);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zscore(key, member);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zunionstore(String dstkey, String... sets) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zunionstore-java.lang.String-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zunionstore", dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zunionstore(dstkey, sets);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public Long zunionstore(String dstkey, ZParams params, String... sets) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/3.0.1/redis/clients/jedis/Jedis.html#zunionstore-java.lang.String-redis.clients.jedis.ZParams-java.lang.String...-
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.zunionstore", dstkey);

    try (Scope ws = trackingOperation.withSpan()) {
      return super.zunionstore(dstkey, params, sets);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }
}

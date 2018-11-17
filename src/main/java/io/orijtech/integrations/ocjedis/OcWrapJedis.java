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
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;

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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#append-java.lang.String-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#bitcount-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#bitcount-java.lang.String-long-long-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#bitfield-java.lang.String-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#bitop-redis.clients.jedis.BitOP-java.lang.String-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#bitpos-java.lang.String-boolean-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#bitpos-java.lang.String-boolean-redis.clients.jedis.BitPosParams-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#blpop-int-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#blpop-int-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#blpop-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#brpop-int-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#brpop-int-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#brpop-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#brpoplpush-java.lang.String-java.lang.String-int-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clientKill-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clientSetname-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#close--
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterAddSlots-int...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterCountKeysInSlot-int-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterDelSlots-int...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterFailover--
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterFlushSlots--
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterForget-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterGetKeysInSlot-int-int-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterInfo--
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterKeySlot-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterMeet-java.lang.String-int-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterNodes--
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterReplicate-java.lang.String-
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
  public String clusterReset(JedisCluster.Reset resetType) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterReset-redis.clients.jedis.JedisCluster.Reset-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterSaveConfig--
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterSetSlotImporting-int-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterSetSlotMigrating-int-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterSetSlotNode-int-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterSetSlotStable-int-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterSlaves-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#clusterSlots--
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#configGet-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#configSet-java.lang.String-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#decr-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#decrBy-java.lang.String-long-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#decrBy-java.lang.String-long-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#del-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#dump-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#echo-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#eval-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#eval-java.lang.String-int-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#eval-java.lang.String-java.util.List-java.util.List-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#evalsha-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#evalsha-java.lang.String-int-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#evalsha-java.lang.String-java.util.List-java.util.List-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#exists-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#exists-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#expire-java.lang.String-int-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#expireAt-java.lang.String-long-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#geoadd-java.lang.String-double-double-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#geoadd-java.lang.String-double-double-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#geodist-java.lang.String-java.lang.String-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#geodist-java.lang.String-java.lang.String-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#geohash-java.lang.String-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#geopos-java.lang.String-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#georadius-java.lang.String-double-double-double-redis.clients.jedis.GeoUnit-
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
  public Long zrevrank(String key, String member) {
    // This method makes a call over the network.
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#zrevrank-java.lang.String-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#zscan-java.lang.String-java.lang.String-redis.clients.jedis.ScanParams-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#zscan-java.lang.String-java.lang.String-redis.clients.jedis.ScanParams-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#zscore-java.lang.String-java.lang.String-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#zunionstore-java.lang.String-java.lang.String...-
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
    // https://static.javadoc.io/redis.clients/jedis/2.9.0/redis/clients/jedis/Jedis.html#zunionstore-java.lang.String-redis.clients.jedis.ZParams-java.lang.String...-
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

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
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;
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
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.bitop");

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
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.blpop");

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
        Observability.createRoundtripTrackingSpan("redis.clients.jedis.Jedis.brpop");

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

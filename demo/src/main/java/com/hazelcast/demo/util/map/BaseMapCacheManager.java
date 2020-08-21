package com.hazelcast.demo.util.map;

import com.hazelcast.core.IMap;
import com.hazelcast.demo.util.enums.MapMetadata;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public interface BaseMapCacheManager<K, V> {
    IMap<K, V> rawMap(MapMetadata metadata);

    V get(K key, MapMetadata metadata);

    Collection<V> getAll(MapMetadata metadata);

    V put(K key, V value, MapMetadata metadata);

    V put(K key, V value, long ttl, TimeUnit timeUnit, MapMetadata metadata);

    void set(K key, V value, MapMetadata metadata);

    void set(K key, V value, long ttl, TimeUnit timeUnit, MapMetadata metadata);

    void delete(K key, MapMetadata metadata);

    V remove(K key, MapMetadata metadata);

    void clearAll(MapMetadata metadata);

}

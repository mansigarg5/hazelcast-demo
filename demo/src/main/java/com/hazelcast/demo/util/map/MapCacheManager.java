package com.hazelcast.demo.util.map;

import com.hazelcast.core.IMap;
import com.hazelcast.demo.provider.CacheConfigurer;
import com.hazelcast.demo.util.enums.MapMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MapCacheManager<K, V> implements BaseMapCacheManager<K, V> {

    private static final Logger LOGGER = LogManager.getLogger(MapCacheManager.class);

    @Override
    public IMap<K, V> rawMap(MapMetadata metadata) {
        if (CacheConfigurer.isCacheEnabled()) {
            try {
                return CacheConfigurer.getHazelcastInstance().getMap(metadata.getName());
            } catch (Exception e) {
                LOGGER.error(String.format("Error while fetching map: %s from cache. Message: %s",
                        metadata.getName(), e.getMessage()));
            }
        }
        return null;
    }

    @Override
    public V get(K key, MapMetadata metadata) {
        if (CacheConfigurer.isCacheEnabled()) {
            try {
                return rawMap(metadata).get(key);
            } catch (Exception e) {
                LOGGER.error(String.format("Error while fetching value for key: %s from map: %s. Message: %s",
                        key, metadata.getName(), e.getMessage()), e);
            }
        }
        return null;
    }

    @Override
    public Collection<V> getAll(MapMetadata metadata) {
        if (CacheConfigurer.isCacheEnabled()) {
            try {
                IMap<K, V> map = rawMap(metadata);
                if (Objects.nonNull(map))
                    return map.values();
            } catch (Exception e) {
                LOGGER.error(String.format("Error while fetching all values from map: %s. Message: %s",
                        metadata.getName(), e.getMessage()), e);
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value, MapMetadata metadata) {
        if (CacheConfigurer.isCacheEnabled()) {
            try {
                rawMap(metadata).put(key, value, metadata.getTTL(), metadata.getTimeUnit());
                return value;
            } catch (Exception e) {
                LOGGER.error(String.format("Error while inserting value for key: %s in map: %s. Message: %s",
                        key, metadata.getName(), e.getMessage()), e);
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value, long ttl, TimeUnit timeUnit, MapMetadata metadata) {
        if (CacheConfigurer.isCacheEnabled()) {
            try {
                rawMap(metadata).put(key, value, ttl, timeUnit);
                return value;
            } catch (Exception e) {
                LOGGER.error(String.format("Error while inserting value for key: %s in map: %s. Message: %s",
                        key, metadata.getName(), e.getMessage()), e);
            }
        }
        return null;
    }

    @Override
    public void set(K key, V value, MapMetadata metadata) {
        if (CacheConfigurer.isCacheEnabled()) {
            try {
                rawMap(metadata).set(key, value, metadata.getTTL(), metadata.getTimeUnit());
            } catch (Exception e) {
                LOGGER.error(String.format("Error while inserting value for key: %s in map: %s. Message: %s",
                        key, metadata.getName(), e.getMessage()), e);
            }
        }
    }

    @Override
    public void set(K key, V value, long ttl, TimeUnit timeUnit, MapMetadata metadata) {
        if (CacheConfigurer.isCacheEnabled()) {
            try {
                rawMap(metadata).set(key, value, ttl, timeUnit);
            } catch (Exception e) {
                LOGGER.error(String.format("Error while inserting value for key: %s in map: %s. Message: %s",
                        key, metadata.getName(), e.getMessage()), e);
            }
        }
    }

    @Override
    public void delete(K key, MapMetadata metadata) {
        if (CacheConfigurer.isCacheEnabled()) {
            try {
                rawMap(metadata).delete(key);
            } catch (Exception e) {
                LOGGER.error(String.format("Error while deleting value for key: %s in map: %s. Message: %s",
                        key, metadata.getName(), e.getMessage()), e);
            }
        }
    }

    @Override
    public V remove(K key, MapMetadata metadata) {
        if (CacheConfigurer.isCacheEnabled()) {
            try {
                return rawMap(metadata).remove(key);
            } catch (Exception e) {
                LOGGER.error(String.format("Error while removing value for key: %s in map: %s. Message: %s",
                        key, metadata.getName(), e.getMessage()), e);
            }
        }
        return null;
    }

    @Override
    public void clearAll(MapMetadata metadata) {
        if (CacheConfigurer.isCacheEnabled()) {
            try {
                CacheConfigurer.getHazelcastInstance().getMap(metadata.getName()).clear();
            } catch (Exception e) {
                LOGGER.error(String.format("Error while clearing map: %s from cache. Message: %s",
                        metadata.getName(), e.getMessage()), e);
            }
        }
    }

    public static boolean nonNull(Object object) {
        return Objects.nonNull(object);
    }

}

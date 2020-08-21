package com.hazelcast.demo.service;

import com.hazelcast.core.IMap;
import com.hazelcast.demo.data.CacheData;
import com.hazelcast.demo.util.enums.MapMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheService {
    @Autowired
    com.hazelcast.demo.util.ConfigCacheManager ConfigCacheManager;

    public IMap<String, CacheData> rawPageMap() {
        return ConfigCacheManager.rawMap(MapMetadata.DEMO_CACHE);
    }

    public CacheData savePage(String value, CacheData configCache) {
        return Objects.nonNull(configCache) && Objects.nonNull(value) ? ConfigCacheManager.put(value,configCache,MapMetadata.DEMO_CACHE) : null;
    }

    public CacheData findAllByValue(String value) {
        return Objects.nonNull(value) ? ConfigCacheManager.get(value,MapMetadata.DEMO_CACHE) : null;
    }

    public void clearAll() {
        ConfigCacheManager.clearAll(MapMetadata.DEMO_CACHE);
    }
}

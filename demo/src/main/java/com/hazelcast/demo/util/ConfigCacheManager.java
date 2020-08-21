package com.hazelcast.demo.util;

import com.hazelcast.demo.data.CacheData;
import com.hazelcast.demo.util.map.MapCacheManager;
import org.springframework.stereotype.Component;

@Component
public class ConfigCacheManager extends MapCacheManager<String, CacheData> {
}


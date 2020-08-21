package com.hazelcast.demo.util.enums;

import java.util.concurrent.TimeUnit;

public enum MapMetadata {
    DEMO_CACHE("DEMO_CACHE",0, TimeUnit.DAYS);

    private String name;
    private long ttl;
    private TimeUnit timeUnit;

    MapMetadata(String name, long time, TimeUnit timeUnit) {
        this.name = name;
        this.ttl = time;
        this.timeUnit = timeUnit;
    }

    public String getName() {
        return name;
    }

    public long getTTL() {
        return ttl;
    }


    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}

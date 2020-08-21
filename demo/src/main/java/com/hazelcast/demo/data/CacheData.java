package com.hazelcast.demo.data;

public class CacheData extends AbstractBaseCacheDTO {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CacheData{" +
                "data='" + data + '\'' +
                '}';
    }
}

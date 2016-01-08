package com.ekart.restClient.entities;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class Headers {

    private final Map<String, String> headerMap;

    // To return a empty Headers instance - more like a convenience method
    public static Headers empty() {

        return new Headers();
    }

    public Headers() {

        headerMap = new HashMap<>();
    }

    public Headers(Map<String, String> headerMap) {

        this.headerMap = Maps.newHashMap(headerMap);
    }

    public void add(String key, String value) {

        headerMap.put(key, value);
    }

    public String get(String key) {

        return headerMap.get(key);
    }

    public Stream<Map.Entry<String, String>> stream() {

        return headerMap.entrySet().stream();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        Headers headers = (Headers) o;

        return headerMap.equals(headers.headerMap);

    }

    @Override
    public int hashCode() {

        return headerMap.hashCode();
    }

    @Override
    public String toString() {

        return "Headers{" +
                "headerMap=" + headerMap +
                '}';
    }
}

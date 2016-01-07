package com.ekart.restClient.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class Headers {

    private final Map<String, String> headerMap;

    public static Headers empty() {

        return new Headers();
    }

    public Headers() {

        headerMap = new HashMap<>();
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
        if (o == null || getClass() != o.getClass()) return false;

        Headers headers = (Headers) o;

        if (!headerMap.equals(headers.headerMap)) return false;

        return true;
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

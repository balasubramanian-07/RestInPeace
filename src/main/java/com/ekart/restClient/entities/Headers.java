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

    public Stream<Map.Entry<String, String>> stream() {

        return headerMap.entrySet().stream();
    }
}

package com.ekart.restClient.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class QueryParams {

    private final Map<String, String> queryParamsMap;

    public static QueryParams empty() {

        return new QueryParams();
    }

    public QueryParams() {

        queryParamsMap = new HashMap<>();
    }

    public void add(String key, String value) {

        queryParamsMap.put(key, value);

    }

    public Stream<Map.Entry<String, String>> stream() {

        return queryParamsMap.entrySet().stream();
    }
}

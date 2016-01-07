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

    @Override
    public String toString() {
        return "QueryParams{" +
                "queryParamsMap=" + queryParamsMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueryParams that = (QueryParams) o;

        if (!queryParamsMap.equals(that.queryParamsMap)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return queryParamsMap.hashCode();
    }
}

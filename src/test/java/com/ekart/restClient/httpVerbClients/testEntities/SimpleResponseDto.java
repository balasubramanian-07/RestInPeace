package com.ekart.restClient.httpVerbClients.testEntities;

import java.io.Serializable;

public class SimpleResponseDto implements Serializable {
    private int id;
    private String key;
    private String value;

    public SimpleResponseDto() {
    }

    public SimpleResponseDto(int id, String key, String value) {

        this.id = id;
        this.key = key;
        this.value = value;
    }

    public int getId() {

        return id;
    }

    public String getKey() {

        return key;
    }

    public String getValue() {

        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleResponseDto that = (SimpleResponseDto) o;

        if (id != that.id) return false;
        if (!key.equals(that.key)) return false;
        if (!value.equals(that.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}

package com.ekart.restClient;

import java.io.Serializable;

public class SampleDomain implements Serializable {

    public SampleDomain() {
    }

    public SampleDomain(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {

        return value;
    }
}

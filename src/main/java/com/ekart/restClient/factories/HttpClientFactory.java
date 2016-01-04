package com.ekart.restClient.factories;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientFactory {

    public CloseableHttpClient get() {

        return HttpClients.createDefault();
    }
}

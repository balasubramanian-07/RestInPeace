package com.ekart.restClient;


import com.ekart.restClient.factories.HttpClientFactory;
import com.ekart.restClient.httpVerbClients.GetClient;
import com.ekart.restClient.httpVerbClients.PostClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public final class RestClient {

    public static final GetClient GET;
    public static final PostClient POST;

    private static final HttpClientFactory httpClientFactory;
    private static final ObjectMapper objectMapper;

    static {

        httpClientFactory = new HttpClientFactory();
        objectMapper = new ObjectMapper();

        GET = new GetClient(httpClientFactory, objectMapper);
        POST = new PostClient(httpClientFactory, objectMapper);
    }
}

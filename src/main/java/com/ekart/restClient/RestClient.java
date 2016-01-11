package com.ekart.restClient;


import com.ekart.restClient.factories.HttpClientFactory;
import com.ekart.restClient.gateways.GetGateway;
import com.ekart.restClient.gateways.PostGateway;
import com.ekart.restClient.requestBuilders.GetRequestBuilder;
import com.ekart.restClient.requestBuilders.PostRequestBuilder;
import com.ekart.restClient.utilities.UriUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class RestClient {

    private final UriUtils uriUtils;
    private final ObjectMapper objectMapper;
    private final HttpClientFactory httpClientFactory;
    private final GetGateway getGateway;
    private final PostGateway postGateway;

    public RestClient() {

        this.httpClientFactory = new HttpClientFactory();
        this.objectMapper = new ObjectMapper();
        this.uriUtils = new UriUtils();
        this.getGateway = new GetGateway(httpClientFactory, objectMapper, uriUtils);
        this.postGateway = new PostGateway(httpClientFactory, objectMapper, uriUtils);
    }

    public RestClient(ObjectMapper objectMapper) {

        this.httpClientFactory = new HttpClientFactory();
        this.objectMapper = objectMapper;
        this.uriUtils = new UriUtils();
        this.getGateway = new GetGateway(httpClientFactory, objectMapper, uriUtils);
        this.postGateway = new PostGateway(httpClientFactory, objectMapper, uriUtils);
    }

    public GetRequestBuilder get() {

        return new GetRequestBuilder(getGateway);
    }

    public PostRequestBuilder post() {

        return new PostRequestBuilder(postGateway);
    }
}

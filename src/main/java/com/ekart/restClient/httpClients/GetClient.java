package com.ekart.restClient.httpClients;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.gateways.GetGateway;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.annotations.VisibleForTesting;

import java.io.IOException;
import java.net.URISyntaxException;

public final class GetClient implements HttpClient {

    private final GetGateway gateway;

    public GetClient() {

        this.gateway = new GetGateway();
    }

    @VisibleForTesting
    GetClient(GetGateway gateway) {

        this.gateway = gateway;
    }

    public <T> RestResponse<T> execute(String url, Class<T> responseType) throws IOException, URISyntaxException {

        return gateway.executeGet(url, QueryParams.empty(), Headers.empty(), responseType);
    }

    public <T> RestResponse<T> execute(String url, QueryParams queryParams, Class<T> responseType) throws IOException, URISyntaxException {

        return gateway.executeGet(url, queryParams, Headers.empty(), responseType);
    }

    public <T> RestResponse<T> execute(String url, Headers headers, Class<T> responseType) throws IOException, URISyntaxException {

        return gateway.executeGet(url, QueryParams.empty(), headers, responseType);
    }

    public <T> RestResponse<T> execute(String url, QueryParams queryParams, Headers headers, Class<T> responseType) throws URISyntaxException, IOException {

        return gateway.executeGet(url, queryParams, headers, responseType);
    }

    public <T> RestResponse<T> execute(String url, TypeReference<T> responseType) throws IOException, URISyntaxException {

        return gateway.executeGet(url, QueryParams.empty(), Headers.empty(), responseType);
    }

    public <T> RestResponse<T> execute(String url, QueryParams queryParams, TypeReference<T> responseType) throws URISyntaxException, IOException {

        return gateway.executeGet(url, queryParams, Headers.empty(), responseType);
    }

    public <T> RestResponse<T> execute(String url, Headers headers, TypeReference<T> responseType) throws IOException, URISyntaxException {

        return gateway.executeGet(url, QueryParams.empty(), headers, responseType);
    }

    public <T> RestResponse<T> execute(String url, QueryParams queryParams, Headers headers, TypeReference<T> responseType) throws URISyntaxException, IOException {

        return gateway.executeGet(url, queryParams, headers, responseType);
    }
}

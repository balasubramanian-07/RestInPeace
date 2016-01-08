package com.ekart.restClient.httpClients;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.gateways.PostGateway;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.annotations.VisibleForTesting;

import java.io.IOException;
import java.net.URISyntaxException;

public final class PostClient implements HttpClient {

    private final PostGateway gateway;

    public PostClient() {

        this.gateway = new PostGateway();
    }

    @VisibleForTesting
    PostClient(PostGateway gateway) {

        this.gateway = gateway;
    }

    public RestResponse execute(String url, Object requestBody) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), Headers.empty());
    }

    public RestResponse execute(String url, Object requestBody, QueryParams queryParams) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, Headers.empty());
    }

    public RestResponse execute(String url, Object requestBody, Headers headers) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), headers);
    }

    public RestResponse execute(String url, Object requestBody, QueryParams queryParams, Headers headers) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, headers);
    }

    public <T> RestResponse<T> execute(String url, Object requestBody, Class<T> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), Headers.empty(), responseType);
    }

    public <T> RestResponse<T> execute(String url, Object requestBody, QueryParams queryParams, Class<T> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, Headers.empty(), responseType);
    }

    public <T> RestResponse<T> execute(String url, Object requestBody, Headers headers, Class<T> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), headers, responseType);
    }

    public <T> RestResponse<T> execute(String url, Object requestBody, QueryParams queryParams, Headers headers, Class<T> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, headers, responseType);
    }

    public <T> RestResponse<T> execute(String url, Object requestBody, TypeReference<T> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), Headers.empty(), responseType);
    }

    public <T> RestResponse<T> execute(String url, Object requestBody, QueryParams queryParams, TypeReference<T> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, Headers.empty(), responseType);
    }

    public <T> RestResponse<T> execute(String url, Object requestBody, Headers headers, TypeReference<T> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), headers, responseType);
    }

    public <T> RestResponse<T> execute(String url, Object requestBody, QueryParams queryParams, Headers headers, TypeReference<T> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, headers, responseType);
    }
}

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

    public <T> RestResponse execute(String url, T requestBody) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), Headers.empty());
    }

    public <T> RestResponse execute(String url, T requestBody, QueryParams queryParams) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, Headers.empty());
    }

    public <T> RestResponse execute(String url, T requestBody, Headers headers) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), headers);
    }

    public <T> RestResponse execute(String url, T requestBody, QueryParams queryParams, Headers headers) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, headers);
    }

    public <T, R> RestResponse<R> execute(String url, T requestBody, Class<R> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), Headers.empty(), responseType);
    }

    public <T, R> RestResponse<R> execute(String url, T requestBody, QueryParams queryParams, Class<R> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, Headers.empty(), responseType);
    }

    public <T, R> RestResponse<R> execute(String url, T requestBody, Headers headers, Class<R> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), headers, responseType);
    }

    public <T, R> RestResponse<R> execute(String url, T requestBody, QueryParams queryParams, Headers headers, Class<R> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, headers, responseType);
    }

    public <T, R> RestResponse<R> execute(String url, T requestBody, TypeReference<R> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), Headers.empty(), responseType);
    }

    public <T, R> RestResponse<R> execute(String url, T requestBody, QueryParams queryParams, TypeReference<R> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, Headers.empty(), responseType);
    }

    public <T, R> RestResponse<R> execute(String url, T requestBody, Headers headers, TypeReference<R> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, QueryParams.empty(), headers, responseType);
    }

    public <T, R> RestResponse<R> execute(String url, T requestBody, QueryParams queryParams, Headers headers, TypeReference<R> responseType) throws IOException, URISyntaxException {

        return gateway.executePost(url, requestBody, queryParams, headers, responseType);
    }
}

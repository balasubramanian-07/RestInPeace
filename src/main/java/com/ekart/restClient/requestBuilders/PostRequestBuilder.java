package com.ekart.restClient.requestBuilders;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.gateways.PostGateway;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class PostRequestBuilder {

    private String url;
    private QueryParams queryParams = QueryParams.empty();
    private Headers headers = Headers.empty();
    private Object requestBody;

    public PostRequestBuilder withUrl(String url) {

        this.url = url;

        return this;
    }

    public PostRequestBuilder withQueryParams(Map<String, String> queryParams) {

        this.queryParams = new QueryParams(queryParams);

        return this;
    }

    public PostRequestBuilder withHeaders(Map<String, String> headers) {

        this.headers = new Headers(headers);

        return this;
    }

    public PostRequestBuilder withRequestBody(Object requestBody) {

        this.requestBody = requestBody;

        return this;
    }

    public <T> RestResponse<T> execute(Class<T> responseType) throws IOException, URISyntaxException {

        PostGateway gateway = new PostGateway();

        return gateway.executePost(url, requestBody, queryParams, headers, responseType);
    }

    public <T> RestResponse<T> execute(TypeReference<T> responseType) throws IOException, URISyntaxException {

        PostGateway gateway = new PostGateway();

        return gateway.executePost(url, requestBody, queryParams, headers, responseType);
    }

    public RestResponse execute() throws IOException, URISyntaxException {

        PostGateway gateway = new PostGateway();

        return gateway.executePost(url, requestBody, queryParams, headers);
    }
}

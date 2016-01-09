package com.ekart.restClient.requestBuilders;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.gateways.GetGateway;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class GetRequestBuilder {

    private final GetGateway gateway;

    private String url;
    private QueryParams queryParams = QueryParams.empty();
    private Headers headers = Headers.empty();

    public GetRequestBuilder(GetGateway gateway) {

        this.gateway = gateway;
    }


    public GetRequestBuilder withUrl(String url) {

        this.url = url;

        return this;
    }

    public GetRequestBuilder withQueryParams(Map<String, String> queryParams) {

        this.queryParams = new QueryParams(queryParams);

        return this;
    }

    public GetRequestBuilder withHeaders(Map<String, String> headers) {

        this.headers = new Headers(headers);

        return this;
    }

    public <T> RestResponse<T> execute(Class<T> responseType) throws IOException, URISyntaxException {

        return gateway.executeGet(url, queryParams, headers, responseType);
    }

    public <T> RestResponse<T> execute(TypeReference<T> responseType) throws IOException, URISyntaxException {

        return gateway.executeGet(url, queryParams, headers, responseType);
    }
}

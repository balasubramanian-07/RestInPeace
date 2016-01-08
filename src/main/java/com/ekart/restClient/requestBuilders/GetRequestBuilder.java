package com.ekart.restClient.requestBuilders;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.httpClients.GetClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class GetRequestBuilder {

    private String url;
    private QueryParams queryParams;
    private Headers headers;

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

        GetClient getClient = new GetClient();
        return getClient.execute(url, queryParams, headers, responseType);
    }
}

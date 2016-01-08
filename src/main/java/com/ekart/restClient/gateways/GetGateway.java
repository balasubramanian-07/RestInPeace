package com.ekart.restClient.gateways;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.factories.HttpClientFactory;
import com.ekart.restClient.utilities.UriUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class GetGateway implements HttpGateway {

    private final HttpClientFactory httpClientFactory;
    private final ObjectMapper objectMapper;
    private final UriUtils uriUtils;

    public GetGateway() {

        httpClientFactory = new HttpClientFactory();
        objectMapper = new ObjectMapper();
        uriUtils = new UriUtils();
    }

    GetGateway(HttpClientFactory httpClientFactory, ObjectMapper objectMapper, UriUtils uriUtils) {

        this.httpClientFactory = httpClientFactory;
        this.objectMapper = objectMapper;
        this.uriUtils = uriUtils;
    }

    // TODO: Error handling
    public <T> RestResponse<T> executeGet(String url, QueryParams queryParams, Headers headers, Class<T> responseType) throws URISyntaxException, IOException {

        String urlWithQueryParams = uriUtils.urlWithQueryParams(url, queryParams);
        HttpGet request = new HttpGet(urlWithQueryParams);
        setJsonHeader(request);
        setCustomHeaders(request, headers);

        try (CloseableHttpClient httpClient = httpClientFactory.get()) {

            CloseableHttpResponse httpResponse = httpClient.execute(request);

            return buildRestResponse(httpResponse, responseType);
        }
    }

    public <T> RestResponse<T> executeGet(String url, QueryParams queryParams, Headers headers, TypeReference<T> responseType) throws URISyntaxException, IOException {

        String urlWithQueryParams = uriUtils.urlWithQueryParams(url, queryParams);
        HttpGet request = new HttpGet(urlWithQueryParams);
        setJsonHeader(request);
        setCustomHeaders(request, headers);

        try (CloseableHttpClient httpClient = httpClientFactory.get()) {

            CloseableHttpResponse httpResponse = httpClient.execute(request);

            return buildRestResponse(httpResponse, responseType);
        }
    }

    private void setJsonHeader(HttpGet request) {

        request.setHeader("Accept", "application/json");
    }

    private void setCustomHeaders(HttpGet request, Headers headers) {

        headers.stream()
                .forEach(h -> request.addHeader(h.getKey(), h.getValue()));
    }

    private <T> RestResponse<T> buildRestResponse(HttpResponse httpResponse, Class<T> responseType) throws IOException {

        return new RestResponse.Builder<T>()
                .withStatusCode(httpResponse.getStatusLine().getStatusCode())
                .withHeaders(mapHeaders(httpResponse.getAllHeaders()))
                .withResponseBody(objectMapper.readValue(httpResponse.getEntity().getContent(), responseType))
                .build();
    }

    private <T> RestResponse<T> buildRestResponse(HttpResponse httpResponse, TypeReference<T> responseType) throws IOException {

        return new RestResponse.Builder<T>()
                .withStatusCode(httpResponse.getStatusLine().getStatusCode())
                .withHeaders(mapHeaders(httpResponse.getAllHeaders()))
                .withResponseBody(objectMapper.readValue(httpResponse.getEntity().getContent(), responseType))
                .build();
    }

    private Headers mapHeaders(Header[] httpHeaders) {

        Headers headers = new Headers();
        Arrays.stream(httpHeaders)
                .forEach(h -> headers.add(h.getName(), h.getValue()));

        return headers;
    }
}

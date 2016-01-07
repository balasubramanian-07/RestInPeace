package com.ekart.restClient.gateways;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.factories.HttpClientFactory;
import com.ekart.restClient.utilities.UriUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class PostGateway implements HttpGateway {

    private final HttpClientFactory httpClientFactory;
    private final ObjectMapper objectMapper;

    public PostGateway() {

        this.httpClientFactory = new HttpClientFactory();
        this.objectMapper = new ObjectMapper();
    }

    PostGateway(HttpClientFactory httpClientFactory, ObjectMapper objectMapper) {

        this.httpClientFactory = httpClientFactory;
        this.objectMapper = objectMapper;
    }

    public <T> RestResponse executePost(String url, T requestBody, QueryParams queryParams, Headers headers) throws URISyntaxException, IOException {

        String urlWithQueryParams = UriUtil.urlWithQueryParams(url, queryParams);

        HttpPost request = new HttpPost(urlWithQueryParams);
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody)));
        setJsonHeaders(request);
        setCustomHeaders(request, headers);

        try (CloseableHttpClient client = httpClientFactory.get()) {

            CloseableHttpResponse httpResponse = client.execute(request);

            return buildRestResponse(httpResponse);
        }
    }

    public <T, R> RestResponse<R> executePost(String url, T requestBody, QueryParams queryParams, Headers headers, Class<R> responseType) throws IOException, URISyntaxException {

        String urlWithQueryParams = UriUtil.urlWithQueryParams(url, queryParams);

        HttpPost request = new HttpPost(urlWithQueryParams);
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody)));
        setJsonHeaders(request);
        setCustomHeaders(request, headers);

        try (CloseableHttpClient client = httpClientFactory.get()) {

            CloseableHttpResponse httpResponse = client.execute(request);

            return buildRestResponse(httpResponse, responseType);
        }
    }

    public <T, R> RestResponse<R> executePost(String url, T requestBody, QueryParams queryParams, Headers headers, TypeReference<R> responseType) throws IOException, URISyntaxException {

        String urlWithQueryParams = UriUtil.urlWithQueryParams(url, queryParams);

        HttpPost request = new HttpPost(urlWithQueryParams);
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody)));
        setJsonHeaders(request);
        setCustomHeaders(request, headers);

        try (CloseableHttpClient client = httpClientFactory.get()) {

            CloseableHttpResponse httpResponse = client.execute(request);

            return buildRestResponse(httpResponse, responseType);
        }
    }

    private RestResponse buildRestResponse(HttpResponse httpResponse) {

        return new RestResponse.Builder()
                .withStatusCode(httpResponse.getStatusLine().getStatusCode())
                .withHeaders(mapHeaders(httpResponse.getAllHeaders()))
                .withResponseBody(null)
                .build();
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

    private void setJsonHeaders(HttpPost request) {

        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
    }

    private void setCustomHeaders(HttpPost request, Headers headers) {

        headers.stream()
                .forEach(h -> request.addHeader(h.getKey(), h.getValue()));
    }
}

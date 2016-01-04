package com.ekart.restClient.httpVerbClients;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.factories.HttpClientFactory;
import com.ekart.restClient.utilities.UriUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;

public class PostClient implements HttpVerbClient {

    private final HttpClientFactory httpClientFactory;
    private final ObjectMapper objectMapper;

    public PostClient(HttpClientFactory httpClientFactory, ObjectMapper objectMapper) {

        this.httpClientFactory = httpClientFactory;
        this.objectMapper = objectMapper;
    }

    public <T> boolean execute(String url, T requestBody) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, QueryParams.empty(), Headers.empty());

        return response.getStatusLine().getStatusCode() == 201;
    }

    public <T> boolean execute(String url, T requestBody, Headers headers) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, QueryParams.empty(), headers);

        return response.getStatusLine().getStatusCode() == 201;
    }

    public <T> boolean execute(String url, T requestBody, QueryParams queryParams) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, queryParams, Headers.empty());

        return response.getStatusLine().getStatusCode() == 201;
    }

    public <T> boolean execute(String url, T requestBody, QueryParams queryParams, Headers headers) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, queryParams, headers);

        return response.getStatusLine().getStatusCode() == 201;
    }

    public <T, R> R execute(String url, T requestBody, Class<R> responseType) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, QueryParams.empty(), Headers.empty());

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T, R> R execute(String url, T requestBody, QueryParams queryParams, Class<R> responseType) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, queryParams, Headers.empty());

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T, R> R execute(String url, T requestBody, Headers headers, Class<R> responseType) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, QueryParams.empty(), headers);

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T, R> R execute(String url, T requestBody, QueryParams queryParams, Headers headers, Class<R> responseType) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, queryParams, headers);

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T, R> R execute(String url, T requestBody, TypeReference<R> responseType) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, QueryParams.empty(), Headers.empty());

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T, R> R execute(String url, T requestBody, QueryParams queryParams, TypeReference<R> responseType) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, queryParams, Headers.empty());

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T, R> R execute(String url, T requestBody, Headers headers, TypeReference<R> responseType) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, QueryParams.empty(), headers);

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T, R> R execute(String url, T requestBody, QueryParams queryParams, Headers headers, TypeReference<R> responseType) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executePost(url, requestBody, queryParams, headers);

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    private <T> CloseableHttpResponse executePost(String url, T requestBody, QueryParams queryParams, Headers headers) throws IOException, URISyntaxException {

        String urlWithQueryParams = UriUtil.urlWithQueryParams(url, queryParams);

        HttpPost request = new HttpPost(urlWithQueryParams);
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(requestBody)));
        setJsonHeaders(request);
        setCustomHeaders(request, headers);

        try (CloseableHttpClient client = getHttpClient()) {

            return client.execute(request);
        }
    }

    private CloseableHttpClient getHttpClient() {

        return httpClientFactory.get();
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

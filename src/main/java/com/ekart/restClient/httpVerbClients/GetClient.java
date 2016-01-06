package com.ekart.restClient.httpVerbClients;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.factories.HttpClientFactory;
import com.ekart.restClient.utilities.UriUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;

public final class GetClient implements HttpVerbClient {

    private final HttpClientFactory httpClientFactory;
    private final ObjectMapper objectMapper;

    public GetClient(HttpClientFactory httpClientFactory, ObjectMapper objectMapper) {

        this.httpClientFactory = httpClientFactory;
        this.objectMapper = objectMapper;
    }

    public <T> T execute(String url, Class<T> responseType) throws IOException, URISyntaxException {

        HttpResponse response = executeGet(url, QueryParams.empty(), Headers.empty());

        // TODO: Error handling
        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T> T execute(String url, QueryParams queryParams, Class<T> responseType) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executeGet(url, queryParams, Headers.empty());

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T> T execute(String url, Headers headers, Class<T> responseType) throws IOException, URISyntaxException {

        CloseableHttpResponse response = executeGet(url, QueryParams.empty(), headers);

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T> T execute(String url, QueryParams queryParams, Headers headers, Class<T> responseType) throws URISyntaxException, IOException {

        HttpResponse response = executeGet(url, queryParams, headers);

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T> T execute(String url, TypeReference<T> responseType) throws IOException, URISyntaxException {

        HttpResponse response = executeGet(url, QueryParams.empty(), Headers.empty());

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T> T execute(String url, QueryParams queryParams, TypeReference<T> responseType) throws URISyntaxException, IOException {

        HttpResponse response = executeGet(url, queryParams, Headers.empty());

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T> T execute(String url, Headers headers, TypeReference<T> responseType) throws IOException, URISyntaxException {

        HttpResponse response = executeGet(url, QueryParams.empty(), headers);

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    public <T> T execute(String url, QueryParams queryParams, Headers headers, TypeReference<T> responseType) throws URISyntaxException, IOException {

        HttpResponse response = executeGet(url, queryParams, headers);

        return objectMapper.readValue(response.getEntity().getContent(), responseType);
    }

    private CloseableHttpResponse executeGet(String url, QueryParams queryParams, Headers headers) throws URISyntaxException, IOException {

        String urlWithQueryParams = UriUtil.urlWithQueryParams(url, queryParams);

        HttpGet request = new HttpGet(urlWithQueryParams);
        setJsonHeader(request);
        setCustomHeaders(request, headers);

        return getHttpClient().execute(request);
    }

    private void setJsonHeader(HttpGet request) {

        request.setHeader("Accept", "application/json");
    }

    private CloseableHttpClient getHttpClient() {

        return httpClientFactory.get();
    }

    private void setCustomHeaders(HttpGet request, Headers headers) {

        headers.stream()
                .forEach(h -> request.addHeader(h.getKey(), h.getValue()));
    }
}

package com.ekart.restClient.httpVerbClients;


import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.factories.HttpClientFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PostClientTest {

    private final String url = "http://example.com";
    private final String queryParamString = "?q1=v1&q2=v2";
    private final String requestBody = "post-request-body";
    private final String requestBodyStringValue = "post-request-body-string-value";
    private final Class<String> responseType = String.class;
    private final TypeReference<String> typeReferenceResponseType = new TypeReference<String>() {
    };
    private final String responseFromUrl = "URL hit successfully";
    private final QueryParams queryParams;
    private final Headers headers;

    private PostClient postClient;

    @Mock
    private HttpClientFactory clientFactory;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private CloseableHttpClient httpClient;
    @Mock
    private CloseableHttpResponse httpResponse;
    @Mock
    private StatusLine statusLine;
    @Mock
    private HttpEntity httpEntity;
    @Mock
    private InputStream inputStream;

    public PostClientTest() {

        queryParams = new QueryParams();
        queryParams.add("q1", "v1");
        queryParams.add("q2", "v2");

        headers = new Headers();
        headers.add("h1", "v1");
        headers.add("h2", "v2");
    }

    @Before
    public void setUp() throws Exception {

        postClient = new PostClient(clientFactory, objectMapper);

        when(clientFactory.get()).thenReturn(httpClient);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(201);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContent()).thenReturn(inputStream);
        when(objectMapper.readValue(inputStream, responseType)).thenReturn(responseFromUrl);
        when(objectMapper.readValue(inputStream, typeReferenceResponseType)).thenReturn(responseFromUrl);
    }

    @Test
    public void shouldMakeRequest() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        boolean result = postClient.execute(url, requestBody);

        assertThat(result, is(true));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url, Headers.empty());
    }

    @Test
    public void shouldMakeRequestWithQueryParams() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        boolean result = postClient.execute(url, requestBody, queryParams);

        assertThat(result, is(true));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url + queryParamString, Headers.empty());
    }

    @Test
    public void shouldMakeRequestWithHeaders() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        boolean result = postClient.execute(url, requestBody, headers);

        assertThat(result, is(true));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url, headers);
    }

    @Test
    public void shouldMakeRequestWithQueryParamsAndHeaders() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        boolean result = postClient.execute(url, requestBody, queryParams, headers);

        assertThat(result, is(true));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url + queryParamString, headers);
    }

    @Test
    public void shouldMakeRequestForResponseType() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        String result = postClient.execute(url, requestBody, responseType);

        assertThat(result, is(responseFromUrl));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url, Headers.empty());
    }

    @Test
    public void shouldMakeRequestForResponseTypeWithQueryParams() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        String result = postClient.execute(url, requestBody, queryParams, responseType);

        assertThat(result, is(responseFromUrl));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url + queryParamString, Headers.empty());
    }

    @Test
    public void shouldMakeRequestForResponseTypeWithHeaders() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        String result = postClient.execute(url, requestBody, headers, responseType);

        assertThat(result, is(responseFromUrl));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url, headers);
    }

    @Test
    public void shouldMakeRequestForResponseTypeWithQueryParamsAndHeaders() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        String result = postClient.execute(url, requestBody, queryParams, headers, responseType);

        assertThat(result, is(responseFromUrl));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url + queryParamString, headers);
    }

    @Test
    public void shouldMakeRequestForTypeReferenceResponseType() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        String result = postClient.execute(url, requestBody, typeReferenceResponseType);

        assertThat(result, is(responseFromUrl));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url, Headers.empty());
    }

    @Test
    public void shouldMakeRequestForTypeReferenceResponseTypeWithQueryParams() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        String result = postClient.execute(url, requestBody, queryParams, typeReferenceResponseType);

        assertThat(result, is(responseFromUrl));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url + queryParamString, Headers.empty());
    }

    @Test
    public void shouldMakeRequestForTypeReferenceResponseTypeWithHeaders() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        String result = postClient.execute(url, requestBody, headers, typeReferenceResponseType);

        assertThat(result, is(responseFromUrl));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url, headers);
    }

    @Test
    public void shouldMakeRequestForTypeReferenceResponseTypeWithQueryParamsAndHeaders() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        String result = postClient.execute(url, requestBody, queryParams, headers, typeReferenceResponseType);

        assertThat(result, is(responseFromUrl));
        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue(), url + queryParamString, headers);
    }

    private void validateRequest(HttpPost request, String url, Headers expectedHeaders) throws IOException {

        assertThat(request.getURI().toString(), is(url));
        assertThat(EntityUtils.toString(request.getEntity()), is(requestBodyStringValue));
        validateHeaders(expectedHeaders, request.getAllHeaders());
    }

    private void validateHeaders(Headers expectedHeaders, Header[] receivedHeaders) {

        expectedHeaders.add("Accept", "application/json");
        expectedHeaders.add("Content-type", "application/json");

        Map<String, String> expectedHeadersMap = expectedHeaders.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Map<String, String> receivedHeadersMap = Arrays.stream(receivedHeaders)
                .collect(Collectors.toMap(Header::getName, Header::getValue));

        MapDifference<String, String> difference = Maps.difference(expectedHeadersMap, receivedHeadersMap);

        assertThat(difference.areEqual(), is(true));
    }
}
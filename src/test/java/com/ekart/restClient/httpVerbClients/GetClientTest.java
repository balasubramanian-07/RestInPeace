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
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
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
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class GetClientTest {

    private final String url = "http://example.com";
    private final Class<String> responseType = String.class;
    private final TypeReference<String> typeReferenceResponseType = new TypeReference<String>() {
    };
    private final String responseFromUrl = "URL hit successfully";
    private final QueryParams queryParams;
    private final Headers headers;

    private GetClient getClient;

    @Mock
    private HttpClientFactory clientFactory;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private CloseableHttpClient httpClient;
    @Mock
    private CloseableHttpResponse httpResponse;
    @Mock
    private HttpEntity httpEntity;
    @Mock
    private InputStream inputStream;

    public GetClientTest() {

        queryParams = new QueryParams();
        queryParams.add("q1", "v1");
        queryParams.add("q2", "v2");

        headers = new Headers();
        headers.add("h1", "v1");
        headers.add("h2", "v2");
    }

    @Before
    public void setup() throws IOException {

        getClient = new GetClient(clientFactory, objectMapper);

        when(clientFactory.get()).thenReturn(httpClient);
        when(httpClient.execute(any(HttpGet.class))).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContent()).thenReturn(inputStream);
        when(objectMapper.readValue(inputStream, responseType)).thenReturn(responseFromUrl);
        when(objectMapper.readValue(inputStream, typeReferenceResponseType)).thenReturn(responseFromUrl);
    }


    @Test
    public void shouldMakeRequest() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);

        String result = getClient.execute(url, responseType);

        verify(httpClient, times(1)).execute(captor.capture());
        assertThat(result, is(responseFromUrl));
        validateRequest(captor.getValue(), url, Headers.empty());
    }

    @Test
    public void shouldMakeRequestWithQueryParams() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);

        String result = getClient.execute(url, queryParams, responseType);

        verify(httpClient, times(1)).execute(captor.capture());
        assertThat(result, is(responseFromUrl));
        validateRequest(captor.getValue(), url + "?q1=v1&q2=v2", Headers.empty());
    }

    @Test
    public void shouldMakeRequestWithHeaders() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);

        String result = getClient.execute(url, headers, responseType);

        verify(httpClient, times(1)).execute(captor.capture());
        assertThat(result, is(responseFromUrl));
        validateRequest(captor.getValue(), url, headers);
    }

    @Test
    public void shouldMakeRequestWithHeadersAndQueryParams() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);

        String result = getClient.execute(url, queryParams, headers, responseType);

        verify(httpClient, times(1)).execute(captor.capture());
        assertThat(result, is(responseFromUrl));
        validateRequest(captor.getValue(), url + "?q1=v1&q2=v2", headers);
    }

    @Test
    public void shouldMakeRequestForTypeReference() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);

        String result = getClient.execute(url, typeReferenceResponseType);

        verify(httpClient, times(1)).execute(captor.capture());
        assertThat(result, is(responseFromUrl));
        validateRequest(captor.getValue(), url, Headers.empty());
    }

    @Test
    public void shouldMakeRequestForTypeReferenceWithQueryParams() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);

        String result = getClient.execute(url, queryParams, typeReferenceResponseType);

        verify(httpClient, times(1)).execute(captor.capture());
        assertThat(result, is(responseFromUrl));
        validateRequest(captor.getValue(), url + "?q1=v1&q2=v2", Headers.empty());
    }

    @Test
    public void shouldMakeRequestForTypeReferenceWithHeaders() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);

        String result = getClient.execute(url, headers, typeReferenceResponseType);

        verify(httpClient, times(1)).execute(captor.capture());
        assertThat(result, is(responseFromUrl));
        validateRequest(captor.getValue(), url, headers);
    }

    @Test
    public void shouldMakeRequestForTypeReferenceWithHeadersAndQueryParams() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);

        String result = getClient.execute(url, queryParams, headers, responseType);

        verify(httpClient, times(1)).execute(captor.capture());
        assertThat(result, is(responseFromUrl));
        validateRequest(captor.getValue(), url + "?q1=v1&q2=v2", headers);
    }

    private void validateRequest(HttpGet request, String url, Headers expectedHeaders) {

        assertThat(request.getURI().toString(), is(url));
        validateHeaders(expectedHeaders, request.getAllHeaders());
    }

    private void validateHeaders(Headers expectedHeaders, Header[] receivedHeaders) {

        Map<String, String> expectedHeadersMap = expectedHeaders.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Map<String, String> receivedHeadersMap = Arrays.stream(receivedHeaders)
                .collect(Collectors.toMap(Header::getName, Header::getValue));
        expectedHeadersMap.put("Accept", "application/json");

        MapDifference<String, String> difference = Maps.difference(expectedHeadersMap, receivedHeadersMap);
        assertThat(difference.entriesInCommon().size(), is(expectedHeadersMap.size()));
    }
}
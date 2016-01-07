package com.ekart.restClient.gateways;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.factories.HttpClientFactory;
import com.ekart.restClient.utilities.UriUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
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
public final class GetGatewayTest {

    private final String url = "http://example.com:8080";
    private final String uriWithQueryParams = url + "?queryParams";
    private final String responseBody = "response-body";
    private final String responseBodyForTypeReference = "response-body-for-type-reference";
    private final int statusCode = 200;
    private final Class<String> responseType = String.class;
    private final TypeReference<String> typeReferenceResponseType = new TypeReference<String>() {};
    private final QueryParams queryParams;
    private final Headers requestHeaders;
    private final Header[] responseHeaders;

    private GetGateway getGateway;

    @Mock
    private HttpClientFactory httpClientFactory;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private CloseableHttpClient httpClient;
    @Mock
    private UriUtils uriUtils;
    @Mock
    private CloseableHttpResponse httpResponse;
    @Mock
    private StatusLine statusLine;
    @Mock
    private HttpEntity httpEntity;
    @Mock
    private InputStream content;

    public GetGatewayTest() {

        queryParams = new QueryParams();
        queryParams.add("q1", "v1");
        queryParams.add("q2", "v2");

        requestHeaders = new Headers();
        requestHeaders.add("reqHeader1", "v1");
        requestHeaders.add("reqHeader2", "v2");

        responseHeaders = new Header[2];
        responseHeaders[0] = new BasicHeader("respHeader1", "v1");
        responseHeaders[1] = new BasicHeader("respHeader2", "v2");
    }

    @Before
    public void setUp() throws Exception {

        getGateway = new GetGateway(httpClientFactory, objectMapper, uriUtils);

        when(uriUtils.urlWithQueryParams(url, queryParams)).thenReturn(uriWithQueryParams);
        when(httpClientFactory.get()).thenReturn(httpClient);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(statusCode);
        when(httpResponse.getAllHeaders()).thenReturn(responseHeaders);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContent()).thenReturn(content);
        when(objectMapper.readValue(content, responseType)).thenReturn(responseBody);
        when(objectMapper.readValue(content, typeReferenceResponseType)).thenReturn(responseBodyForTypeReference);
    }

    @Test
    public void shouldExecuteGetWithResponseType() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);

        RestResponse<String> restResponse = getGateway.executeGet(url, queryParams, requestHeaders, responseType);

        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue());
        validateResponseWithBody(restResponse, responseBody);
    }

    @Test
    public void shouldExecuteGetWithTypeReferenceResponseType() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);

        RestResponse<String> restResponse = getGateway.executeGet(url, queryParams, requestHeaders, typeReferenceResponseType);

        verify(httpClient, times(1)).execute(captor.capture());
        validateRequest(captor.getValue());
        validateResponseWithBody(restResponse, responseBodyForTypeReference);
    }

    private void validateRequest(HttpGet request) throws IOException {

        assertThat(request.getURI().toString(), is(uriWithQueryParams));
        validateHeaders(requestHeaders, request.getAllHeaders());
    }

    private void validateResponseWithBody(RestResponse<String> restResponse, String responseBody) {

        assertThat(restResponse.getStatusCode(), is(statusCode));
        validateHeaders(responseHeaders, restResponse.getHeaders());
        assertThat(restResponse.getResponseBody(), is(responseBody));
    }

    private void validateHeaders(Headers expectedHeaders, Header[] receivedHeaders) {

        //TODO: This can be moved outside
        expectedHeaders.add("Accept", "application/json");

        Map<String, String> expectedHeadersMap = expectedHeaders.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Map<String, String> receivedHeadersMap = Arrays.stream(receivedHeaders)
                .collect(Collectors.toMap(Header::getName, Header::getValue));

        compareHeaders(expectedHeadersMap, receivedHeadersMap);
    }

    private void validateHeaders(Header[] expectedHeaders, Headers receivedHeaders) {

        Map<String, String> expectedHeadersMap = Arrays.stream(expectedHeaders)
                .collect(Collectors.toMap(Header::getName, Header::getValue));
        Map<String, String> receivedHeadersMap = receivedHeaders.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        compareHeaders(expectedHeadersMap, receivedHeadersMap);
    }

    private void compareHeaders(Map<String, String> headers1, Map<String, String> headers2) {

        MapDifference<String, String> difference = Maps.difference(headers1, headers2);

        assertThat(difference.areEqual(), is(true));
    }
}
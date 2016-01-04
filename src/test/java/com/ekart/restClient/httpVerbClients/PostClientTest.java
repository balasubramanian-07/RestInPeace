package com.ekart.restClient.httpVerbClients;


import com.ekart.restClient.factories.HttpClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostClientTest {

    private final String url = "http://example.com";
    private final String requestBody = "post-request-body";
    private final String requestBodyStringValue = "post-request-body-string-value";

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

    @Before
    public void setUp() throws Exception {

        postClient = new PostClient(clientFactory, objectMapper);

        when(clientFactory.get()).thenReturn(httpClient);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(201);
    }

    @Test
    public void shouldMakeRequest() throws IOException, URISyntaxException {

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);

        when(objectMapper.writeValueAsString(requestBody)).thenReturn(requestBodyStringValue);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        boolean result = postClient.execute(url, requestBody);

//        assertThat(result, is(true));
//        verify(httpClient, times(1)).execute(captor.capture());
//        HttpPost request = captor.getValue();
//        assertThat(request.getURI().toString(), is(url));
//        System.out.println(request.getEntity());
    }
}
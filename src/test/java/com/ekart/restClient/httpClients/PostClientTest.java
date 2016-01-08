package com.ekart.restClient.httpClients;


import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.gateways.PostGateway;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public final class PostClientTest {

    private final String url = "http://example.com";
    private final String requestBody = "This is a sample request";
    private final Class<String> responseType = String.class;
    private final TypeReference<String> typeReferenceResponseType = new TypeReference<String>() {};
    private final QueryParams queryParams;
    private final Headers headers;
    private final RestResponse restResponse;
    private final RestResponse<String> restResponseWithBody;

    private PostClient postClient;

    @Mock
    private PostGateway gateway;


    public PostClientTest() {

        queryParams = new QueryParams();
        queryParams.add("q1", "v1");
        queryParams.add("q2", "v2");

        headers = new Headers();
        headers.add("h1", "v1");
        headers.add("h2", "v2");

        restResponse = new RestResponse.Builder()
                .withStatusCode(200)
                .withHeaders(Headers.empty())
                .withResponseBody(null)
                .build();

        restResponseWithBody = new RestResponse.Builder<String>()
                .withStatusCode(200)
                .withHeaders(Headers.empty())
                .withResponseBody("Url hit successfully")
                .build();
    }

    @Before
    public void setUp() throws Exception {

        postClient = new PostClient(gateway);
    }

    @Test
    public void shouldMakeRequest() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, QueryParams.empty(), Headers.empty())).thenReturn(restResponse);

        assertThat(postClient.execute(url, requestBody), is(restResponse));
    }

    @Test
    public void shouldMakeRequestWithQueryParams() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, queryParams, Headers.empty())).thenReturn(restResponse);

        assertThat(postClient.execute(url, requestBody, queryParams), is(restResponse));
    }

    @Test
    public void shouldMakeRequestWithHeaders() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, QueryParams.empty(), headers)).thenReturn(restResponse);

        assertThat(postClient.execute(url, requestBody, headers), is(restResponse));

    }

    @Test
    public void shouldMakeRequestWithQueryParamsAndHeaders() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, queryParams, headers)).thenReturn(restResponse);

        assertThat(postClient.execute(url, requestBody, queryParams, headers), is(restResponse));
    }

    @Test
    public void shouldMakeRequestForResponseType() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, QueryParams.empty(), Headers.empty(), responseType)).thenReturn(restResponseWithBody);

        assertThat(postClient.execute(url, requestBody, responseType), is(restResponseWithBody));
    }

    @Test
    public void shouldMakeRequestForResponseTypeWithQueryParams() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, queryParams, Headers.empty(), responseType)).thenReturn(restResponseWithBody);

        assertThat(postClient.execute(url, requestBody, queryParams, responseType), is(restResponseWithBody));
    }

    @Test
    public void shouldMakeRequestForResponseTypeWithHeaders() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, QueryParams.empty(), headers, responseType)).thenReturn(restResponseWithBody);

        assertThat(postClient.execute(url, requestBody, headers, responseType), is(restResponseWithBody));
    }

    @Test
    public void shouldMakeRequestForResponseTypeWithQueryParamsAndHeaders() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, queryParams, headers, responseType)).thenReturn(restResponseWithBody);

        assertThat(postClient.execute(url, requestBody, queryParams, headers, responseType), is(restResponseWithBody));
    }

    @Test
    public void shouldMakeRequestForTypeReferenceResponseType() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, QueryParams.empty(), Headers.empty(), typeReferenceResponseType)).thenReturn(restResponseWithBody);

        assertThat(postClient.execute(url, requestBody, typeReferenceResponseType), is(restResponseWithBody));
    }

    @Test
    public void shouldMakeRequestForTypeReferenceResponseTypeWithQueryParams() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, queryParams, Headers.empty(), typeReferenceResponseType)).thenReturn(restResponseWithBody);

        assertThat(postClient.execute(url, requestBody, queryParams, typeReferenceResponseType), is(restResponseWithBody));
    }

    @Test
    public void shouldMakeRequestForTypeReferenceResponseTypeWithHeaders() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, QueryParams.empty(), headers, typeReferenceResponseType)).thenReturn(restResponseWithBody);

        assertThat(postClient.execute(url, requestBody, headers, typeReferenceResponseType), is(restResponseWithBody));
    }

    @Test
    public void shouldMakeRequestForTypeReferenceResponseTypeWithQueryParamsAndHeaders() throws IOException, URISyntaxException {

        when(gateway.executePost(url, requestBody, queryParams, headers, typeReferenceResponseType)).thenReturn(restResponseWithBody);

        assertThat(postClient.execute(url, requestBody, queryParams, headers, typeReferenceResponseType), is(restResponseWithBody));
    }
}
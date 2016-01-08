package com.ekart.restClient.httpClients;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.gateways.GetGateway;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public final class GetClientTest {

    private final String url = "http://example.com";
    private final Class<String> responseType = String.class;
    private final TypeReference<String> typeReferenceResponseType = new TypeReference<String>() {};
    private final QueryParams queryParams;
    private final Headers headers;
    private final RestResponse<String> restResponse;

    @Mock
    private GetGateway getGateway;

    private GetClient getClient;

    public GetClientTest() {

        queryParams = new QueryParams();
        queryParams.add("q1", "v1");
        queryParams.add("q2", "v2");

        headers = new Headers();
        headers.add("h1", "v1");
        headers.add("h2", "v2");

        restResponse = new RestResponse.Builder<String>()
                .withHeaders(headers)
                .withStatusCode(201)
                .withResponseBody("Url hit successfully")
                .build();

    }

    @Before
    public void setup() throws IOException {

        getClient = new GetClient(getGateway);
    }


    @Test
    public void shouldMakeRequest() throws IOException, URISyntaxException {

        when(getGateway.executeGet(url, QueryParams.empty(), Headers.empty(), responseType)).thenReturn(restResponse);

        assertThat(getClient.execute(url, responseType), is(restResponse));
    }

    @Test
    public void shouldMakeRequestWithQueryParams() throws IOException, URISyntaxException {

        when(getGateway.executeGet(url, queryParams, Headers.empty(), responseType)).thenReturn(restResponse);

        assertThat(getClient.execute(url, queryParams, responseType), is(restResponse));
    }

    @Test
    public void shouldMakeRequestWithHeaders() throws IOException, URISyntaxException {

        when(getGateway.executeGet(url, QueryParams.empty(), headers, responseType)).thenReturn(restResponse);

        assertThat(getClient.execute(url, headers, responseType), is(restResponse));
    }

    @Test
    public void shouldMakeRequestWithHeadersAndQueryParams() throws IOException, URISyntaxException {

        when(getGateway.executeGet(url, queryParams, headers, responseType)).thenReturn(restResponse);

        assertThat(getClient.execute(url, queryParams, headers, responseType), is(restResponse));
    }

    @Test
    public void shouldMakeRequestForTypeReference() throws IOException, URISyntaxException {

        when(getGateway.executeGet(url, QueryParams.empty(), Headers.empty(), typeReferenceResponseType)).thenReturn(restResponse);

        assertThat(getClient.execute(url, typeReferenceResponseType), is(restResponse));
    }

    @Test
    public void shouldMakeRequestForTypeReferenceWithQueryParams() throws IOException, URISyntaxException {

        when(getGateway.executeGet(url, queryParams, Headers.empty(), typeReferenceResponseType)).thenReturn(restResponse);

        assertThat(getClient.execute(url, queryParams, typeReferenceResponseType), is(restResponse));
    }

    @Test
    public void shouldMakeRequestForTypeReferenceWithHeaders() throws IOException, URISyntaxException {

        when(getGateway.executeGet(url, QueryParams.empty(), headers, typeReferenceResponseType)).thenReturn(restResponse);

        assertThat(getClient.execute(url, headers, typeReferenceResponseType), is(restResponse));
    }

    @Test
    public void shouldMakeRequestForTypeReferenceWithHeadersAndQueryParams() throws IOException, URISyntaxException {

        when(getGateway.executeGet(url, queryParams, headers, typeReferenceResponseType)).thenReturn(restResponse);

        assertThat(getClient.execute(url, queryParams, headers, typeReferenceResponseType), is(restResponse));
    }
}
package com.ekart.restClient.requestBuilders;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.gateways.GetGateway;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;


//TODO: Should add test to verify the return object in all test cases!
@RunWith(MockitoJUnitRunner.class)
public class GetRequestBuilderTest {

    private final String url = "http://example.com";
    private final Class<String> responseType = String.class;
    private final TypeReference<String> typeReferenceResponseType = new TypeReference<String>() {};

    private GetRequestBuilder getRequestBuilder;

    @Mock
    private GetGateway getGateway;

    @Before
    public void setup() {

        getRequestBuilder = new GetRequestBuilder(getGateway);
    }

    @Test
    public void shouldSetTheRequestUrl() throws IOException, URISyntaxException {

        getRequestBuilder
                .withUrl(url)
                .execute(responseType);

        verify(getGateway).executeGet(eq(url), any(QueryParams.class), any(Headers.class), eq(responseType));
    }

    @Test
    public void shouldSetTheQueryParams() throws IOException, URISyntaxException {

        Map<String, String> queryParamsMap = ImmutableMap.of("q1", "v1", "q2", "v2");

        getRequestBuilder
                .withQueryParams(queryParamsMap)
                .execute(responseType);

        ArgumentCaptor<QueryParams> captor = ArgumentCaptor.forClass(QueryParams.class);

        verify(getGateway).executeGet(any(String.class), captor.capture(), any(Headers.class), eq(responseType));
        QueryParams queryParams = captor.getValue();
        assertThat(queryParams, is(new QueryParams(queryParamsMap)));
    }

    @Test
    public void shouldSetEmptyQueryParamsWhenNotSet() throws IOException, URISyntaxException {

        getRequestBuilder
                .execute(responseType);

        ArgumentCaptor<QueryParams> captor = ArgumentCaptor.forClass(QueryParams.class);

        verify(getGateway).executeGet(any(String.class), captor.capture(), any(Headers.class), eq(responseType));
        QueryParams queryParams = captor.getValue();
        assertThat(queryParams, is(QueryParams.empty()));
    }

    @Test
    public void shouldSetTheHeaders() throws IOException, URISyntaxException {

        Map<String, String> headersMap = ImmutableMap.of("h1", "v1", "h2", "v2");

        getRequestBuilder
                .withHeaders(headersMap)
                .execute(responseType);

        ArgumentCaptor<Headers> captor = ArgumentCaptor.forClass(Headers.class);

        verify(getGateway).executeGet(any(String.class), any(QueryParams.class), captor.capture(), eq(responseType));
        Headers headers = captor.getValue();
        assertThat(headers, is(new Headers(headersMap)));
    }

    @Test
    public void shouldSetEmptyHeadersWhenNotSet() throws IOException, URISyntaxException {

        getRequestBuilder
                .execute(responseType);

        ArgumentCaptor<Headers> captor = ArgumentCaptor.forClass(Headers.class);

        verify(getGateway).executeGet(any(String.class), any(QueryParams.class), captor.capture(), eq(responseType));
        Headers headers = captor.getValue();
        assertThat(headers, is(Headers.empty()));
    }

    @Test
    public void shouldSetTheRequestUrlForTypeReferenceResponseType() throws IOException, URISyntaxException {

        getRequestBuilder
                .withUrl(url)
                .execute(typeReferenceResponseType);

        verify(getGateway).executeGet(eq(url), any(QueryParams.class), any(Headers.class), eq(typeReferenceResponseType));
    }

    @Test
    public void shouldSetTheQueryParamsForTypeReferenceResponseType() throws IOException, URISyntaxException {

        Map<String, String> queryParamsMap = ImmutableMap.of("q1", "v1", "q2", "v2");

        getRequestBuilder
                .withQueryParams(queryParamsMap)
                .execute(typeReferenceResponseType);

        ArgumentCaptor<QueryParams> captor = ArgumentCaptor.forClass(QueryParams.class);

        verify(getGateway).executeGet(any(String.class), captor.capture(), any(Headers.class), eq(typeReferenceResponseType));
        QueryParams queryParams = captor.getValue();
        assertThat(queryParams, is(new QueryParams(queryParamsMap)));
    }

    @Test
    public void shouldSetEmptyQueryParamsWhenNotSetForTypeReferenceResponseType() throws IOException, URISyntaxException {

        getRequestBuilder
                .execute(typeReferenceResponseType);

        ArgumentCaptor<QueryParams> captor = ArgumentCaptor.forClass(QueryParams.class);

        verify(getGateway).executeGet(any(String.class), captor.capture(), any(Headers.class), eq(typeReferenceResponseType));
        QueryParams queryParams = captor.getValue();
        assertThat(queryParams, is(QueryParams.empty()));
    }

    @Test
    public void shouldSetTheHeadersForTypeReferenceResponseType() throws IOException, URISyntaxException {

        Map<String, String> headersMap = ImmutableMap.of("h1", "v1", "h2", "v2");

        getRequestBuilder
                .withHeaders(headersMap)
                .execute(typeReferenceResponseType);

        ArgumentCaptor<Headers> captor = ArgumentCaptor.forClass(Headers.class);

        verify(getGateway).executeGet(any(String.class), any(QueryParams.class), captor.capture(), eq(typeReferenceResponseType));
        Headers headers = captor.getValue();
        assertThat(headers, is(new Headers(headersMap)));
    }

    @Test
    public void shouldSetEmptyHeadersWhenNotSetForTypeReferenceResponseType() throws IOException, URISyntaxException {

        getRequestBuilder
                .execute(typeReferenceResponseType);

        ArgumentCaptor<Headers> captor = ArgumentCaptor.forClass(Headers.class);

        verify(getGateway).executeGet(any(String.class), any(QueryParams.class), captor.capture(), eq(typeReferenceResponseType));
        Headers headers = captor.getValue();
        assertThat(headers, is(Headers.empty()));
    }
}
package com.ekart.restClient.requestBuilders;

import com.ekart.restClient.entities.Headers;
import com.ekart.restClient.entities.QueryParams;
import com.ekart.restClient.gateways.PostGateway;
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
public class PostRequestBuilderTest {

    private final String url = "http://example.com";
    private final Class<String> responseType = String.class;
    private final TypeReference<String> typeReferenceResponseType = new TypeReference<String>() {};
    private final Object requestBody = new Object();

    private PostRequestBuilder postRequestBuilder;

    @Mock
    private PostGateway postGateway;

    @Before
    public void setup() {

        postRequestBuilder = new PostRequestBuilder(postGateway);
    }

    @Test
    public void shouldSetUrl() throws IOException, URISyntaxException {

        postRequestBuilder
                .withUrl(url)
                .execute(String.class);

        verify(postGateway).executePost(eq(url), any(Object.class), any(QueryParams.class), any(Headers.class), eq(responseType));
    }

    @Test
    public void shouldSetQueryParams() throws IOException, URISyntaxException {

        Map<String, String> queryParamsMap = ImmutableMap.of("q1", "v1", "q2", "v2");

        postRequestBuilder
                .withQueryParams(queryParamsMap)
                .execute(responseType);

        ArgumentCaptor<QueryParams> captor = ArgumentCaptor.forClass(QueryParams.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), captor.capture(), any(Headers.class), eq(responseType));
        QueryParams queryParams = captor.getValue();
        assertThat(queryParams, is(new QueryParams(queryParamsMap)));
    }

    @Test
    public void shouldSetEmptyQueryParamsWhenNotSet() throws IOException, URISyntaxException {

        postRequestBuilder
                .execute(responseType);

        ArgumentCaptor<QueryParams> captor = ArgumentCaptor.forClass(QueryParams.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), captor.capture(), any(Headers.class), eq(responseType));
        QueryParams queryParams = captor.getValue();
        assertThat(queryParams, is(QueryParams.empty()));
    }

    @Test
    public void shouldSetHeaders() throws IOException, URISyntaxException {

        Map<String, String> headersMap = ImmutableMap.of("h1", "v1", "h2", "v2");

        postRequestBuilder
                .withHeaders(headersMap)
                .execute(responseType);

        ArgumentCaptor<Headers> captor = ArgumentCaptor.forClass(Headers.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), any(QueryParams.class), captor.capture(), eq(responseType));
        Headers headers = captor.getValue();
        assertThat(headers, is(new Headers(headersMap)));
    }

    @Test
    public void shouldSetEmptyHeadersWhenNotSet() throws IOException, URISyntaxException {

        postRequestBuilder
                .execute(responseType);

        ArgumentCaptor<Headers> captor = ArgumentCaptor.forClass(Headers.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), any(QueryParams.class), captor.capture(), eq(responseType));
        Headers headers = captor.getValue();
        assertThat(headers, is(Headers.empty()));
    }

    @Test
    public void shouldSetRequestBody() throws IOException, URISyntaxException {

        postRequestBuilder
                .withRequestBody(requestBody)
                .execute(responseType);

        verify(postGateway).executePost(any(String.class), eq(requestBody), any(QueryParams.class), any(Headers.class), eq(responseType));
    }

    @Test
    public void shouldSetResponseType() throws IOException, URISyntaxException {

        postRequestBuilder
                .execute(responseType);

        verify(postGateway).executePost(any(String.class), any(), any(QueryParams.class), any(Headers.class), eq(responseType));
    }

    @Test
    public void shouldSetTheRequestUrlForTypeReferenceResponseType() throws IOException, URISyntaxException {

        postRequestBuilder
                .withUrl(url)
                .execute(typeReferenceResponseType);

        verify(postGateway).executePost(eq(url), any(Object.class), any(QueryParams.class), any(Headers.class), eq(typeReferenceResponseType));
    }

    @Test
    public void shouldSetTheQueryParamsForTypeReferenceResponseType() throws IOException, URISyntaxException {

        Map<String, String> queryParamsMap = ImmutableMap.of("q1", "v1", "q2", "v2");

        postRequestBuilder
                .withQueryParams(queryParamsMap)
                .execute(typeReferenceResponseType);

        ArgumentCaptor<QueryParams> captor = ArgumentCaptor.forClass(QueryParams.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), captor.capture(), any(Headers.class), eq(typeReferenceResponseType));
        QueryParams queryParams = captor.getValue();
        assertThat(queryParams, is(new QueryParams(queryParamsMap)));
    }

    @Test
    public void shouldSetEmptyQueryParamsWhenNotSetForTypeReferenceResponseType() throws IOException, URISyntaxException {

        postRequestBuilder
                .execute(typeReferenceResponseType);

        ArgumentCaptor<QueryParams> captor = ArgumentCaptor.forClass(QueryParams.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), captor.capture(), any(Headers.class), eq(typeReferenceResponseType));
        QueryParams queryParams = captor.getValue();
        assertThat(queryParams, is(QueryParams.empty()));
    }


    @Test
    public void shouldSetTheHeadersForTypeReferenceResponseType() throws IOException, URISyntaxException {

        Map<String, String> headersMap = ImmutableMap.of("h1", "v1", "h2", "v2");

        postRequestBuilder
                .withHeaders(headersMap)
                .execute(typeReferenceResponseType);

        ArgumentCaptor<Headers> captor = ArgumentCaptor.forClass(Headers.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), any(QueryParams.class), captor.capture(), eq(typeReferenceResponseType));
        Headers headers = captor.getValue();
        assertThat(headers, is(new Headers(headersMap)));
    }

    @Test
    public void shouldSetEmptyHeadersWhenNotSetForTypeReferenceResponseType() throws IOException, URISyntaxException {

        postRequestBuilder
                .execute(typeReferenceResponseType);

        ArgumentCaptor<Headers> captor = ArgumentCaptor.forClass(Headers.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), any(QueryParams.class), captor.capture(), eq(typeReferenceResponseType));
        Headers headers = captor.getValue();
        assertThat(headers, is(Headers.empty()));
    }

    @Test
    public void shouldSetRequestBodyForTypeReferenceResponseType() throws IOException, URISyntaxException {

        postRequestBuilder
                .withRequestBody(requestBody)
                .execute(typeReferenceResponseType);

        verify(postGateway).executePost(any(String.class), eq(requestBody), any(QueryParams.class), any(Headers.class), eq(typeReferenceResponseType));
    }

    @Test
    public void shouldSetTypeReferenceResponseType() throws IOException, URISyntaxException {

        postRequestBuilder
                .execute(typeReferenceResponseType);

        verify(postGateway).executePost(any(String.class), any(), any(QueryParams.class), any(Headers.class), eq(typeReferenceResponseType));
    }

    @Test
    public void shouldSetTheRequestUrlEmptyResponseType() throws IOException, URISyntaxException {

        postRequestBuilder
                .withUrl(url)
                .execute();

        verify(postGateway).executePost(eq(url), any(Object.class), any(QueryParams.class), any(Headers.class));
    }

    @Test
    public void shouldSetTheQueryParamsEmptyResponseType() throws IOException, URISyntaxException {

        Map<String, String> queryParamsMap = ImmutableMap.of("q1", "v1", "q2", "v2");

        postRequestBuilder
                .withQueryParams(queryParamsMap)
                .execute();

        ArgumentCaptor<QueryParams> captor = ArgumentCaptor.forClass(QueryParams.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), captor.capture(), any(Headers.class));
        QueryParams queryParams = captor.getValue();
        assertThat(queryParams, is(new QueryParams(queryParamsMap)));
    }

    @Test
    public void shouldSetEmptyQueryParamsWhenNotSetEmptyResponseType() throws IOException, URISyntaxException {

        postRequestBuilder
                .execute();

        ArgumentCaptor<QueryParams> captor = ArgumentCaptor.forClass(QueryParams.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), captor.capture(), any(Headers.class));
        QueryParams queryParams = captor.getValue();
        assertThat(queryParams, is(QueryParams.empty()));
    }

    @Test
    public void shouldSetTheHeadersEmptyResponseType() throws IOException, URISyntaxException {

        Map<String, String> headersMap = ImmutableMap.of("h1", "v1", "h2", "v2");

        postRequestBuilder
                .withHeaders(headersMap)
                .execute();

        ArgumentCaptor<Headers> captor = ArgumentCaptor.forClass(Headers.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), any(QueryParams.class), captor.capture());
        Headers headers = captor.getValue();
        assertThat(headers, is(new Headers(headersMap)));
    }

    @Test
    public void shouldSetEmptyHeadersWhenNotSetEmptyResponseType() throws IOException, URISyntaxException {

        postRequestBuilder
                .execute();

        ArgumentCaptor<Headers> captor = ArgumentCaptor.forClass(Headers.class);

        verify(postGateway).executePost(any(String.class), any(Object.class), any(QueryParams.class), captor.capture());
        Headers headers = captor.getValue();
        assertThat(headers, is(Headers.empty()));
    }

    @Test
    public void shouldSetRequestBodyEmptyResponseType() throws IOException, URISyntaxException {

        postRequestBuilder
                .withRequestBody(requestBody)
                .execute();

        verify(postGateway).executePost(any(String.class), eq(requestBody), any(QueryParams.class), any(Headers.class));
    }
}
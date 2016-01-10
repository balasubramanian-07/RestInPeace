package com.ekart.restClient.utilities;

import com.ekart.restClient.entities.QueryParams;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public final class UriUtilsTest {

    private final String url = "http://example.com";
    private final UriUtils uriUtils = new UriUtils();

    @Test
    public void shouldBuildUrlWithQueryParams() throws URISyntaxException {

        QueryParams queryParams = new QueryParams(ImmutableMap.of("q1", "v1", "q2", "v2"));

        String urlWithQueryParams = uriUtils.urlWithQueryParams(url, queryParams);

        assertThat(urlWithQueryParams, is(url + "?q1=v1&q2=v2"));
    }

    @Test
    public void shouldBuildUrlWithEmptyQueryParams() throws URISyntaxException {

        String urlWithQueryParams = uriUtils.urlWithQueryParams(url, QueryParams.empty());

        assertThat(urlWithQueryParams, is(url));
    }

}
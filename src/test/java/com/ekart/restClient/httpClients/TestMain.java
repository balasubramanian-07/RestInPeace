package com.ekart.restClient.httpClients;

import com.ekart.restClient.RestClient;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.httpClients.testEntities.SimpleRequestDto;
import com.ekart.restClient.httpClients.testEntities.SimpleResponseDto;
import com.google.common.collect.Maps;
import org.apache.http.HttpStatus;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public final class TestMain {

    // These urls pertain to a local wms-template-server that I had started before running these tests
    // So, these tests are ignored by default
    // This is more to demonstrate how to use it, will have to make it better
    private static final String GET_URL = "http://127.0.0.1:8080/api/dummy/get-api";
    private static final String POST_URL = "http://127.0.0.1:8080/api/dummy/post-api";

    @Test
    @Ignore
    public void testFluentGetRequest() throws IOException, URISyntaxException {

        SimpleRequestDto simpleRequestDto = new SimpleRequestDto(1, "foo", "bar");

        RestResponse<SimpleResponseDto> restResponse = new RestClient()
                .get()
                .withUrl(GET_URL)
                .withQueryParams(Maps.newHashMap())
                .withHeaders(Maps.newHashMap())
                .execute(SimpleResponseDto.class);

        assertThat(restResponse.getStatusCode(), is(HttpStatus.SC_OK));
        SimpleResponseDto responseDto = restResponse.getResponseBody();
        assertThat(responseDto.getId(), is(simpleRequestDto.getId()));
        assertThat(responseDto.getKey(), is(simpleRequestDto.getKey()));
        assertThat(responseDto.getValue(), is(simpleRequestDto.getValue()));
    }

    @Test
    @Ignore
    public void testFluentPostRequest() throws IOException, URISyntaxException {

        SimpleRequestDto simpleRequestDto = new SimpleRequestDto(1, "foo", "bar");

        RestResponse<SimpleResponseDto> restResponse = new RestClient()
                .post()
                .withUrl(POST_URL)
                .withQueryParams(Maps.newHashMap())
                .withHeaders(Maps.newHashMap())
                .withRequestBody(simpleRequestDto)
                .execute(SimpleResponseDto.class);

        assertThat(restResponse.getStatusCode(), is(HttpStatus.SC_OK));
        SimpleResponseDto responseDto = restResponse.getResponseBody();
        assertThat(responseDto.getId(), is(simpleRequestDto.getId()));
        assertThat(responseDto.getKey(), is(simpleRequestDto.getKey()));
        assertThat(responseDto.getValue(), is(simpleRequestDto.getValue()));
    }
}

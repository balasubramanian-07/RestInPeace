package com.ekart.restClient.httpVerbClients;

import com.ekart.restClient.RestClient;
import com.ekart.restClient.entities.RestResponse;
import com.ekart.restClient.httpVerbClients.testEntities.SimpleRequestDto;
import com.ekart.restClient.httpVerbClients.testEntities.SimpleResponseDto;
import org.apache.http.HttpStatus;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestMain {

    // These urls pertain to a local server that I had started before running these tests
    // So, these tests are ignored by default
    private static final String GET_URL = "http://127.0.0.1:8080/api/dummy/get-api";
    private static final String POST_URL = "http://127.0.0.1:8080/api/dummy/post-api";

    @Test
    @Ignore
    public void testGetRequest() throws IOException, URISyntaxException {

        SimpleRequestDto simpleRequestDto = new SimpleRequestDto(1, "foo", "bar");

        RestResponse<SimpleResponseDto> restResponse = RestClient.GET.execute(GET_URL, SimpleResponseDto.class);

        assertThat(restResponse.getStatusCode(), is(HttpStatus.SC_OK));
        SimpleResponseDto responseDto = restResponse.getResponseBody();
        assertThat(responseDto.getId(), is(simpleRequestDto.getId()));
        assertThat(responseDto.getKey(), is(simpleRequestDto.getKey()));
        assertThat(responseDto.getValue(), is(simpleRequestDto.getValue()));
    }

    @Test
    @Ignore
    public void testPostRequest() throws IOException, URISyntaxException {

        SimpleRequestDto simpleRequestDto = new SimpleRequestDto(1, "foo", "bar");

        RestResponse<SimpleResponseDto> restResponse = RestClient.POST.execute(POST_URL, simpleRequestDto, SimpleResponseDto.class);

        assertThat(restResponse.getStatusCode(), is(HttpStatus.SC_OK));
        SimpleResponseDto responseDto = restResponse.getResponseBody();
        assertThat(responseDto.getId(), is(simpleRequestDto.getId()));
        assertThat(responseDto.getKey(), is(simpleRequestDto.getKey()));
        assertThat(responseDto.getValue(), is(simpleRequestDto.getValue()));
    }
}

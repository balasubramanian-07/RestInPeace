package com.ekart.restClient.utilities;

import com.ekart.restClient.entities.QueryParams;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

public class UriUtils {

    public String urlWithQueryParams(String url, QueryParams queryParams) throws URISyntaxException {

        URIBuilder builder = new URIBuilder(url);
        queryParams.stream()
                .forEach(e -> builder.addParameter(e.getKey(), e.getValue()));

        return builder.build().toString();
    }
}

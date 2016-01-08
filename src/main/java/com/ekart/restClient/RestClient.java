package com.ekart.restClient;


import com.ekart.restClient.requestBuilders.GetRequestBuilder;
import com.ekart.restClient.requestBuilders.PostRequestBuilder;

public final class RestClient {

    public GetRequestBuilder get() {

        return new GetRequestBuilder();
    }

    public PostRequestBuilder post() {

        return new PostRequestBuilder();
    }
}

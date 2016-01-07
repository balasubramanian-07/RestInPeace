package com.ekart.restClient;


import com.ekart.restClient.httpVerbClients.GetClient;
import com.ekart.restClient.httpVerbClients.PostClient;

public final class RestClient {

    public static final GetClient GET;
    public static final PostClient POST;

    static {

        GET = new GetClient();
        POST = new PostClient();
    }
}

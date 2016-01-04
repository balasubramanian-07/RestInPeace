package com.ekart.restClient;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        RestClient restClient = new RestClient();

        SampleDomain requestBody = new SampleDomain("Dare receive my request!!");

        SampleDomain response = RestClient.POST.execute("http://127.0.0.1:8080/api/dummy/post-url", requestBody, SampleDomain.class);

        System.out.println(response.getValue());
    }
}

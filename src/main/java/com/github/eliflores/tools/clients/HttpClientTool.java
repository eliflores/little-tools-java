package com.github.eliflores.tools.clients;

import com.github.eliflores.tools.http.HttpClient;

public class HttpClientTool {
    private static final String BASE_URL = "http://httpbin.org/";
    private static final String GET_TARGET = "get";
    private static final String POST_TARGET = "post";

    public static void main(String[] args) {
        HttpClient myHttpClient = new HttpClient();
        String getResponse = myHttpClient.sendGetRequest(BASE_URL + GET_TARGET);
        System.out.println("GET RS => " + getResponse);

        String postResponse = myHttpClient.sendPostRequest(BASE_URL + POST_TARGET, "Hello World");
        System.out.println("POST RS => " + postResponse);

    }
}

package com.github.eliflores.tools.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UncheckedIOException;

public class HttpClient {
    private static final int HTTP_OK_STATUS = 200;


    public String sendGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        ResponseHandler<String> responseHandler = getResponseHandler();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();) {
            return httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            throw new UncheckedIOException("Error while sending HTTP Request.", e);
        }
    }

    public String sendPostRequest(String url, String request) {
        HttpPost httpPost = new HttpPost(url);
        ResponseHandler<String> responseHandler = getResponseHandler();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpEntity httpEntity = new StringEntity(request);
            httpPost.setEntity(httpEntity);
            return httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            throw new UncheckedIOException("Error while sending HTTP Request.", e);
        }
    }

    private static ResponseHandler<String> getResponseHandler() {
        return httpResponse -> {
            int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
            String response = null;
            if (responseStatusCode == HTTP_OK_STATUS) {
                HttpEntity httpEntity = httpResponse.getEntity();
                response = httpEntity != null ? EntityUtils.toString(httpEntity) : null;
            }
            return response;
        };
    }
}

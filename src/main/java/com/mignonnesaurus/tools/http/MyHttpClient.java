package com.mignonnesaurus.tools.http;

import com.mignonnesaurus.tools.exception.ExceptionUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MyHttpClient {

    private static final int HTTP_OK_STATUS = 200;


    public String sendGetRequest(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        ResponseHandler<String> responseHandler = getResponseHandler();
        try {
            return httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            throw ExceptionUtil.createException("Error while reading Http response.", e);
        } finally {
            close(httpClient);
        }
    }

    public String sendPostRequest(String url, String request) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        ResponseHandler<String> responseHandler = getResponseHandler();
        try {
            HttpEntity httpEntity = new StringEntity(request);
            httpPost.setEntity(httpEntity);
            return httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            throw ExceptionUtil.createException("Error while reading Http response.", e);
        } finally {
            close(httpClient);
        }
    }

    private static void close(CloseableHttpClient httpClient) {
        try {
            httpClient.close();
        } catch (IOException e) {
            throw ExceptionUtil.createException("Error while reading Http response.", e);
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

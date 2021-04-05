package com.github.eliflores.tools.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class HttpClientTest {
    private static final WireMockServer wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());

    @BeforeAll
    static void setUp() {
        wireMockServer.start();
    }

    @AfterAll
    static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void sendGetRequest() {
        wireMockServer.stubFor(
                get(urlPathEqualTo("/a/resource"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBody("The response content.")));

        HttpClient httpClient = new HttpClient();
        String url = "http://localhost:" + wireMockServer.port() + "/a/resource";
        String response = httpClient.sendGetRequest(url);

        assertThat(response, is("The response content."));
    }

    @Test
    void sendPostRequest() {
        wireMockServer.stubFor(
                post(urlPathEqualTo("/a/resource"))
                        .withRequestBody(equalTo("The request body."))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBody("The response content.")));

        HttpClient httpClient = new HttpClient();
        String url = "http://localhost:" + wireMockServer.port() + "/a/resource";
        String response = httpClient.sendPostRequest(url, "The request body.");

        assertThat(response, is("The response content."));
    }
}

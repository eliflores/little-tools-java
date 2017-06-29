package com.mignonnesaurus.tools.url;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class UrlUtilTest {

    private static final String ENCODING = "UTF-8";

    @Test
    void encode() {
        String url = UrlUtil.encode("http://www.helloworld.com?name=han solo&date=20/07/2015", ENCODING);
        assertThat(url, is("http%3A%2F%2Fwww.helloworld.com%3Fname%3Dhan+solo%26date%3D20%2F07%2F2015"));
    }

    @Test
    void decode() {
        String url = UrlUtil.decode("http%3A%2F%2Fwww.helloworld.com%3Fname%3Dhan+solo%26date%3D20%2F07%2F2015", ENCODING);
        assertThat(url, is("http://www.helloworld.com?name=han solo&date=20/07/2015"));
    }
}
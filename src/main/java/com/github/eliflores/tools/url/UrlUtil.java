package com.github.eliflores.tools.url;

import com.github.eliflores.tools.exception.LittleToolsException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public final class UrlUtil {
    private UrlUtil() {
    }

    public static String encode(String url, String enc) {
        try {
            return URLEncoder.encode(url, enc);
        } catch (UnsupportedEncodingException e) {
            throw new LittleToolsException("Could not encode the url: " + url, e);
        }
    }

    public static String decode(String url, String enc) {
        try {
            return URLDecoder.decode(url, enc);
        } catch (UnsupportedEncodingException e) {
            throw new LittleToolsException("Could not decode the url: " + url, e);
        }
    }
}

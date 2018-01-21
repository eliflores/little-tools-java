package com.mignonnesaurus.tools.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mignonnesaurus.tools.exception.ExceptionUtil;

import java.io.IOException;

public final class JsonWriter {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    public String toJson(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (IOException e) {
            throw ExceptionUtil.createException("Error while reading JSON", e);
        }
    }

    private static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}

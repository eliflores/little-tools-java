package com.github.eliflores.tools.json;

import com.github.eliflores.tools.json.model.Person;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.io.InputStream;

class JsonWriterTest {
    private final JsonWriter jsonWriter = new JsonWriter();

    @Test
    void toJson() throws Exception {
        Person person = new Person();
        person.setFirstName("Luke");
        person.setLastName("Skywalker");

        String expectedJson = getJsonFromFile("person.json");
        String actualJson = jsonWriter.toJson(person);
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    private String getJsonFromFile(String fileName) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        return IOUtils.toString(inputStream);
    }
}
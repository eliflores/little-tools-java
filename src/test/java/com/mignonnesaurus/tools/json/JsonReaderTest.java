package com.mignonnesaurus.tools.json;

import com.mignonnesaurus.tools.json.model.Person;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class JsonReaderTest {
    private JsonReader jsonReader = new JsonReader();

    @Test
    void fromJson() throws Exception {
        String json = getJsonFromFile("person.json");
        Person person = jsonReader.fromJson(json, Person.class);
        assertExpectedPerson(person, "Luke", "Skywalker");
    }

    @Test
    void fromJsonList() throws Exception {
        String json = getJsonFromFile("people.json");
        List<Person> people = jsonReader.fromJsonList(json, Person.class);
        assertThat(people.size(), is(2));
        assertExpectedPerson(people.get(0), "Luke", "Skywalker");
        assertExpectedPerson(people.get(1), "Han", "Solo");
    }

    private void assertExpectedPerson(Person person, String firstName, String lastName) {
        assertThat(person.getFirstName(), is(firstName));
        assertThat(person.getLastName(), is(lastName));
    }

    private String getJsonFromFile(String fileName) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        return IOUtils.toString(inputStream);
    }
}
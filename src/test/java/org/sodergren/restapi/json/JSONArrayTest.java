package org.sodergren.restapi.json;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JSONArrayTest {

    @Test
    public void shouldHandleBooleanData() {
        String json = JSON.makeArray()
                .add(true)
                .add(false)
                .build()
                .toJson();
        assertThat(json, equalTo("[true,false]"));
    }

    @Test
    public void shouldHandleNumberData() {
        String json = JSON.makeArray()
                .add(1234.123)
                .add(Long.MAX_VALUE)
                .add(Integer.MAX_VALUE)
                .build()
                .toJson();
        assertThat(json, equalTo(String.format("[%s,%s,%s]", 1234.123, Long.MAX_VALUE, Integer.MAX_VALUE)));
    }

    @Test
    public void shouldHandleStringValues() {
        String json = JSON.makeArray()
                .add("string1")
                .add("string2")
                .build()
                .toJson();
        assertThat(json, equalTo(String.format("[\"%s\",\"%s\"]", "string1", "string2")));
    }

    @Test
    public void shouldHandleNestedObjects() {
        String json = JSON.makeArray()
                .add(JSON.makeObject()
                        .put("nested", JSON.makeObject()
                                .put("inner", "inner")
                                .build())
                        .build())
                .add(JSON.makeObject()
                        .put("nested2", JSON.makeArray()
                                .add("string1")
                                .add("string2")
                                .build())
                        .build())
                .build()
                .toJson();
        assertThat(json, equalTo("[{\"nested\":{\"inner\":\"inner\"}},{\"nested2\":[\"string1\",\"string2\"]}]"));
    }
}

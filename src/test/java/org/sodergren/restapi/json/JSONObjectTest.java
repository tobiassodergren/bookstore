package org.sodergren.restapi.json;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JSONObjectTest {

    @Test
    public void shouldHandleBooleanData() {
        String json = JSON.makeObject().put("isBoolean", true).put("isNotSet", false).build().toJson();
        assertThat(json, containsString("\"isBoolean\":true"));
        assertThat(json, containsString("\"isNotSet\":false"));
    }

    @Test
    public void shouldHandleNumberData() {
        String json = JSON.makeObject().put("double", 1234.123).put("long", Long.MAX_VALUE).put("int", Integer.MAX_VALUE).build().toJson();
        assertThat(json, containsString("\"double\":1234.123"));
        assertThat(json, containsString("\"long\":" + Long.MAX_VALUE));
        assertThat(json, containsString("\"int\":" + Integer.MAX_VALUE));
    }

    @Test
    public void shouldHandleStringValue() {
        String json = JSON.makeObject().put("string", "string").build().toJson();
        assertThat(json, containsString("\"string\":\"string\""));
    }

    @Test
    public void shouldHandleNestedObjects() {
        String json = JSON.makeObject()
                .put("nested", JSON.makeObject()
                        .put("inner", "inner")
                        .build())
                .build()
                .toJson();
        assertThat(json, equalTo("{\"nested\":{\"inner\":\"inner\"}}"));
    }
}

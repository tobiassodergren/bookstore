package org.sodergren.restapi.json;

public class JSONObjectBuilder {
    private final JSONObject resultJson;

    JSONObjectBuilder() {
        this.resultJson = new JSONObject();
    }

    public JSONObjectBuilder put(String key, Object value) {
        resultJson.put(key, value);
        return this;
    }

    public JSONObject build() {
        return resultJson;
    }
}

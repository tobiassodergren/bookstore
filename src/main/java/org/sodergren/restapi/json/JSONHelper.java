package org.sodergren.restapi.json;

class JSONHelper {

    public static void appendValue(StringBuilder builder, Object value) {
        if (value instanceof Number || value instanceof Boolean) {
            builder.append(value.toString());
        }
        if (value == null) {
            builder.append("null");
        }

        if (value instanceof String) {
            builder.append('"').append(value).append('"');
        }

        if (value instanceof JSONObject) {
            builder.append(((JSONObject) value).toJson());
        }

        if (value instanceof JSONArray) {
            builder.append(((JSONArray) value).toJson());
        }
    }
}

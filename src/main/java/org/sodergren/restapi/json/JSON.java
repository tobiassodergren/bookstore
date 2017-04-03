package org.sodergren.restapi.json;

public class JSON {
    public static JSONObjectBuilder makeObject() {
        return new JSONObjectBuilder();
    }

    public static JSONArrayBuilder makeArray() {
        return new JSONArrayBuilder();
    }
}

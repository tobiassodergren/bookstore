package org.sodergren.restapi.json;

import java.util.ArrayList;
import java.util.Iterator;

public class JSONArray extends ArrayList<Object> {

    public String toJson() {
        StringBuilder builder = new StringBuilder();
        Iterator<Object> iterator = this.iterator();

        builder.append('[');
        while (iterator.hasNext()) {
            JSONHelper.appendValue(builder, iterator.next());

            if (iterator.hasNext()) {
                builder.append(",");
            }
        }
        builder.append(']');

        return builder.toString();
    }
}

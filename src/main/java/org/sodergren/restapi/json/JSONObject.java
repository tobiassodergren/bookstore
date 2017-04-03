package org.sodergren.restapi.json;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class JSONObject extends LinkedHashMap<String, Object> {
    public String toJson() {
        StringBuilder builder = new StringBuilder();
        Iterator<Map.Entry<String, Object>> iterator = this.entrySet().iterator();

        builder.append('{');
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();

            builder.append('"').append(entry.getKey()).append('"')
                    .append(":");

            JSONHelper.appendValue(builder, entry.getValue());

            if (iterator.hasNext()) {
                builder.append(',');
            }
        }
        builder.append('}');

        return builder.toString();
    }

}
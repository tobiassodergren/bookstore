package org.sodergren.restapi.json;

import java.util.Collection;

public class JSONArrayBuilder {

    private final JSONArray resultJson;

    JSONArrayBuilder() {
        this.resultJson = new JSONArray();
    }

    public JSONArrayBuilder add(Object value) {
        resultJson.add(value);
        return this;
    }

    public <E, Y> JSONArrayBuilder addItems(Collection<E> items, Processor<E, Y> processor) {
        items.forEach((e) -> resultJson.add(processor.process(e)));
        return this;
    }

    public JSONArray build() {
        return resultJson;
    }

    public interface Processor<X, Y> {
        Y process(X x);
    }
}

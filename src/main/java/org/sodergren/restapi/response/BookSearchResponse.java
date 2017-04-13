package org.sodergren.restapi.response;

import org.sodergren.model.entities.Book;
import org.sodergren.restapi.json.JSON;
import org.sodergren.restapi.json.JSONArrayBuilder;
import org.sodergren.restapi.json.JSONObject;

public class BookSearchResponse {
    private final Book[] list;

    public BookSearchResponse(Book[] list) {
        this.list = list;
    }

    public String toJson() {
        JSONArrayBuilder result = JSON.makeArray();

        for (Book book : list) {
            result.add(getBookJson(book));
        }

        return result.build().toJson();
    }

    private JSONObject getBookJson(Book book) {
        return JSON.makeObject()
                .put("uuid", book.getId().toString())
                .put("author", book.getAuthor())
                .put("title", book.getTitle())
                .put("price", book.getPrice())
                .build();
    }
}

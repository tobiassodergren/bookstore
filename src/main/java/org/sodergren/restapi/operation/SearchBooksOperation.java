package org.sodergren.restapi.operation;

import org.sodergren.model.entities.Book;
import org.sodergren.model.entities.BookList;
import org.sodergren.restapi.response.BookSearchResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SearchBooksOperation extends OperationBase {
    private final BookList bookStore;
    private final String query;

    public SearchBooksOperation(BookList bookStore, String query) {
        this.bookStore = bookStore;
        this.query = query;
    }

    @Override
    public Response execute() {
        Book[] list = bookStore.list(query);

        BookSearchResponse response = new BookSearchResponse(list);

        return Response.ok(response.toJson(), MediaType.APPLICATION_JSON_TYPE).build();
    }
}

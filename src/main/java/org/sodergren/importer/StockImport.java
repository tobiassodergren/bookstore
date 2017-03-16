package org.sodergren.importer;

import org.sodergren.model.entities.Book;

public class StockImport {
    public final Book book;
    public final int quantity;

    public StockImport(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockImport{" +
                "book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}

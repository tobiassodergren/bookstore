package org.sodergren.importer;

import org.sodergren.model.entities.Book;

public class StockImport {
    final Book book;
    final int quantity;

    StockImport(Book book, int quantity) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockImport that = (StockImport) o;

        if (quantity != that.quantity) return false;
        return book.equals(that.book);
    }

    @Override
    public int hashCode() {
        int result = book.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}

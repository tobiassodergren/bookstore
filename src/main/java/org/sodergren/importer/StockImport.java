package org.sodergren.importer;

import org.sodergren.model.entity.Book;

/**
 * Class containing import data for initializing a BookList implementation with books.
 */
public class StockImport {
    final Book book;
    final int quantity;

    StockImport(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockImport {" +
                "book=" + book +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        StockImport that = (StockImport) other;

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

package org.sodergren.bookstore;

import org.sodergren.model.entities.Book;

import java.util.UUID;

public class BookStock {
    private final UUID bookId;
    private final int quantity;
    private final Book book;

    BookStock(UUID id, Book book, int quantity) {
        this.bookId = id;
        this.book = book;
        this.quantity = quantity;
    }

    public BookStock add(int amount) {
        return new BookStock(bookId, book, quantity + amount);
    }


    public BookStock subtract(int amount) throws StockTooSmallException {
        if (quantity < amount) {
            throw new StockTooSmallException();
        }
        return new BookStock(bookId, book, quantity - amount);
    }

    public int getQuantity() {
        return quantity;
    }

    public Book getBook() {
        return book;
    }
}

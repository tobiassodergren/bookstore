package org.sodergren.bookstore;

import org.sodergren.model.entity.Book;

import java.util.UUID;

/**
 * Keeps track of the quantity in store for each book.
 * <p>
 * Immutable.
 */
public class BookStock {
    private final UUID bookId;
    private final int quantity;
    private final Book book;

    BookStock(UUID id, Book book, int quantity) {
        this.bookId = id;
        this.book = book;
        this.quantity = quantity >= 0 ? quantity : 0;
    }

    BookStock add(int amount) {
        return new BookStock(bookId, book, quantity + amount);
    }


    BookStock subtract(int amount) throws StockTooSmallException {
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

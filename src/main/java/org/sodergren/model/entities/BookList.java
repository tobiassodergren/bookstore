package org.sodergren.model.entities;

import org.sodergren.bookstore.NotFoundException;

import java.util.UUID;

/**
 * Required API for the store
 */
public interface BookList {
    Book[] list(String searchString);

    boolean add(Book book, int quantity);

    int[] buy(Book... books);

    Book getById(UUID uuid) throws NotFoundException;
}

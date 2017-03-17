package org.sodergren.model.entities;

/**
 * Required API for the store
 */
public interface BookList {
    Book[] list(String searchString);

    boolean add(Book book, int quantity);

    int[] buy(Book... books);
}

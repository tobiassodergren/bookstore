package org.sodergren.bookstore;

import org.junit.Before;
import org.junit.Test;
import org.sodergren.model.entity.Book;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BookStoreInventoryTest {

    private BookStore bookStore;

    @Before
    public void beforeEachClass() {
        bookStore = new BookStore();
        bookStore.add(new Book("Pelle i djungeln", "Jan Lööf", BigDecimal.valueOf(29.99)), 2);
    }

    @Test
    public void addingNonExistingBookShouldReturnTrue() {
        Book book = new Book("Emil i Lönneberga", "Astrid Lindgren", BigDecimal.valueOf(75));
        boolean isNewBook = bookStore.add(book, 3);

        assertThat(isNewBook, equalTo(true));
        assertThat(bookStore.list("").length, equalTo(2));
        assertThat(bookStore.quantityFor(book), equalTo(3));
    }

    @Test
    public void addingExistingBookShouldReturnFalse() {
        Book book = new Book("Pelle i djungeln", "Jan Lööf", BigDecimal.valueOf(29.99));
        boolean isNewBook = bookStore.add(book, 3);

        assertThat(isNewBook, equalTo(false));
        assertThat(bookStore.list("").length, equalTo(1));
        assertThat(bookStore.quantityFor(book), equalTo(5));
    }
}

package org.sodergren.bookstore;

import org.junit.Before;
import org.junit.Test;
import org.sodergren.model.entity.Book;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BookStoreSearchTest {

    private static final Book PELLE = new Book("Pelle i djungeln", "Jan Lööf", BigDecimal.valueOf(29.99));
    private static final Book ALFONS = new Book("Alfons Åberg", "Gunilla Bergström", BigDecimal.valueOf(85.5));
    private static final Book EMIL = new Book("Emil i Lönneberga", "Astrid Lindgren", BigDecimal.valueOf(75));
    private static final Book KRAKAN = new Book("Den lilla kråkan", "Pelle Bergstrand", BigDecimal.valueOf(15));
    private BookStore bookStore;

    @Before
    public void beforeEachClass() {
        bookStore = new BookStore();
        bookStore.add(PELLE, 2);
        bookStore.add(ALFONS, 2);
        bookStore.add(EMIL, 2);
        bookStore.add(KRAKAN, 1);
    }

    @Test
    public void shouldFindEmil() {

        Book[] result = bookStore.list("Emil");
        assertThat(result.length, equalTo(1));
        assertThat(result[0].getId(), equalTo(EMIL.getId()));

        result = bookStore.list("Astrid");
        assertThat(result.length, equalTo(1));
        assertThat(result[0].getId(), equalTo(EMIL.getId()));
    }

    @Test
    public void shouldFind2Pelle() {
        Book[] result = bookStore.list("Pelle");
        assertThat(result.length, equalTo(2));

        assertThat(result[0].getId(), anyOf(equalTo(PELLE.getId()), equalTo(KRAKAN.getId())));
        assertThat(result[1].getId(), anyOf(equalTo(PELLE.getId()), equalTo(KRAKAN.getId())));
    }
}

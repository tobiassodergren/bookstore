package org.sodergren.bookstore;

import org.junit.Before;
import org.junit.Test;
import org.sodergren.model.BookOrderStatus;
import org.sodergren.model.entity.Book;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.sodergren.model.BookOrderStatus.*;

public class BookStorePurchaseTest {

    private static final Book PELLE = new Book("Pelle i djungeln", "Jan Lööf", BigDecimal.valueOf(29.99));
    private static final Book ALFONS = new Book("Alfons Åberg", "Gunilla Bergström", BigDecimal.valueOf(85.5));
    private static final Book EMIL = new Book("Emil i Lönneberga", "Astrid Lindgren", BigDecimal.valueOf(75));
    private BookStore bookStore;

    @Before
    public void beforeEachClass() {
        bookStore = new BookStore();
        bookStore.add(PELLE, 2);
        bookStore.add(ALFONS, 2);
        bookStore.add(EMIL, 2);
    }

    @Test
    public void shoppingShouldReturnCorrectResult() {

        int[] result = bookStore.buy(PELLE, ALFONS, PELLE, EMIL, PELLE, new Book("Max Napp", "Barbro Lindgren", BigDecimal.valueOf(60)));

        assertThat(result.length, equalTo(6));
        assertThat(BookOrderStatus.get(result[0]), equalTo(OK));
        assertThat(BookOrderStatus.get(result[1]), equalTo(OK));
        assertThat(BookOrderStatus.get(result[2]), equalTo(OK));
        assertThat(BookOrderStatus.get(result[3]), equalTo(OK));
        assertThat(BookOrderStatus.get(result[4]), equalTo(NOT_IN_STOCK));
        assertThat(BookOrderStatus.get(result[5]), equalTo(DOES_NOT_EXIST));

        assertThat(bookStore.quantityFor(PELLE), equalTo(0));
        assertThat(bookStore.quantityFor(ALFONS), equalTo(1));
        assertThat(bookStore.quantityFor(EMIL), equalTo(1));

        assertThat(bookStore.list("djungeln").length, equalTo(0));
        assertThat(bookStore.list("Alfons").length, equalTo(1));
    }

}

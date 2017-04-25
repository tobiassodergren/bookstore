package org.sodergren.cart;

import org.junit.Test;
import org.sodergren.model.entity.Book;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ServerCartTest {

    private static final Book BOOK_1 = new Book("title 1", "author 1", BigDecimal.valueOf(15.4));
    private static final Book BOOK_2 = new Book("title 2", "author 2", BigDecimal.valueOf(7));
    private static final Book BOOK_3 = new Book("title 3", "author 3", BigDecimal.valueOf(0.23));

    @Test
    public void shouldSumPriceAndQuantity() {
        ServerCart serverCart = new ServerCart();

        serverCart.addItemToList(BOOK_1, 3);
        serverCart.addItemToList(BOOK_2, 5);
        serverCart.addItemToList(BOOK_3, 1);

        assertThat(serverCart.getTotal(), equalTo(BigDecimal.valueOf(15.4 * 3 + 7 * 5 + 0.23)));
    }

    @Test
    public void shouldRemoveItem() {
        ServerCart serverCart = new ServerCart();

        serverCart.addItemToList(BOOK_1, 3);
        serverCart.addItemToList(BOOK_2, 4);

        serverCart.removeItem(BOOK_1.getId());

        assertThat(serverCart.getItems().size(), equalTo(1));
        assertThat(serverCart.getItems().get(0), equalTo(new ServerCartItem(BOOK_2, 3)));
    }

    @Test
    public void shouldAddQuantityToCartItem() {
        ServerCart serverCart = new ServerCart();

        serverCart.addItemToList(BOOK_1, 3);
        serverCart.addItemToList(BOOK_1, 2);

        assertThat(serverCart.getItems().size(), equalTo(1));
        assertThat(serverCart.getItems().get(0).getCost(), equalTo(BigDecimal.valueOf(5 * BOOK_1.getPrice().doubleValue())));
    }
}

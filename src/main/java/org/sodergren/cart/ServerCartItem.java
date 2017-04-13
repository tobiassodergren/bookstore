package org.sodergren.cart;

import org.sodergren.model.entity.Book;
import org.sodergren.model.entity.CartItem;
import org.sodergren.model.entity.CartItemDescription;

import java.math.BigDecimal;
import java.util.UUID;

public class ServerCartItem implements CartItem {
    private final Book book;
    private final int quantity;

    public ServerCartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity < 0 ? 0 : quantity;
    }

    @Override
    public CartItemDescription getDescription() {
        return new CartItemDescription(book.getId(), book.getTitle(), book.getAuthor(), this.getPrice(), quantity);
    }

    @Override
    public BigDecimal getPrice() {
        return this.book.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public ServerCartItem add(int quantity) {
        if (this.quantity + quantity < 0) {
            return new ServerCartItem(book, 0);
        }
        return new ServerCartItem(book, this.quantity + quantity);
    }

    @Override
    public UUID getId() {
        return book.getId();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        ServerCartItem cartItem = (ServerCartItem) other;

        return book.equals(cartItem.book);
    }

    @Override
    public int hashCode() {
        return book.hashCode();
    }
}

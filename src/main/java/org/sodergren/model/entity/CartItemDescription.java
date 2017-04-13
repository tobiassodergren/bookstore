package org.sodergren.model.entity;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * This is an immutable copy of the CartItem state
 */
public class CartItemDescription {
    public final UUID id;
    public final String title;
    public final String author;
    public final BigDecimal price;
    public final int quantity;

    public CartItemDescription(UUID uuid, String title, String author, BigDecimal price, int quantity) {
        this.id = uuid;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }
}

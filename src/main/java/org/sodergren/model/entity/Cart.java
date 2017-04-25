package org.sodergren.model.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface Cart {
    UUID getId();

    void addItemToList(Book book, int quantity);

    List<CartItem> getItems();

    void removeItem(UUID cartItemId);

    BigDecimal getTotal();

    List<UUID> toOrder();
}

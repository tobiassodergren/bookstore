package org.sodergren.cart;

import org.sodergren.model.entity.Book;
import org.sodergren.model.entity.Cart;
import org.sodergren.model.entity.CartItem;

import java.math.BigDecimal;
import java.util.*;

public class ServerCart implements Cart {

    private final UUID id;
    private final Map<UUID, ServerCartItem> items = new HashMap<>();

    ServerCart() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public synchronized void addItemToList(Book book, int quantity) {

        UUID uuid = book.getId();

        if (items.containsKey(uuid)) {
            ServerCartItem cartItem = items.get(uuid);
            items.put(uuid, cartItem.add(quantity));
        } else {
            items.put(uuid, new ServerCartItem(book, quantity));
        }
    }

    @Override
    public synchronized List<CartItem> getItems() {
        return Collections.unmodifiableList(new ArrayList<CartItem>(items.values()));
    }

    @Override
    public synchronized void removeItem(UUID id) {
        items.remove(id);
    }

    @Override
    public synchronized BigDecimal getTotal() {
        return items.values()
                .stream()
                .map(ServerCartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerCart that = (ServerCart) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

package org.sodergren.cart;

import org.sodergren.model.entity.Cart;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class CartRepository {

    // TODO remove carts that was least recently used. Or use guava Cache
    Map<UUID, ServerCart> carts = new LinkedHashMap<>(16, 0.75f, true);

    public Cart getCartById(UUID cartId) {

        if (cartId == null) {
            return createCart();
        }

        ServerCart cart = carts.get(cartId);

        if (cart == null) {
            return createCart();
        }

        return cart;
    }

    public Cart createCart() {
        ServerCart cart = new ServerCart();
        carts.put(cart.getId(), cart);
        return cart;
    }

}

package org.sodergren.cart;

import org.sodergren.model.entity.Cart;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class CartRepository {

    // TODO Replace this with Google Guava cache instead
    Map<UUID, ServerCart> carts = new CartCache<>();

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

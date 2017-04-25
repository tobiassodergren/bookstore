package org.sodergren.cart;

import org.sodergren.model.entity.Cart;

import java.util.*;

/**
 * Unfriendly implementation of a session based cart. This should be replaced with a client-side
 * cart, stored either in local store or as a cookie.
 *
 * But, for the purpose of this assignment it will do.
 *
 */
public class CartRepository {

    private final Map<UUID, ServerCart> carts = new CartCache<>();

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

    public Collection<ServerCart> getCarts() {
        return carts.values();
    }
}

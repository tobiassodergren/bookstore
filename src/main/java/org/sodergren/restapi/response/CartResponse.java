package org.sodergren.restapi.response;

import org.sodergren.model.entities.Cart;
import org.sodergren.restapi.json.JSON;

// TODO Jaxon

public class CartResponse {
    private final Cart cart;

    public CartResponse(Cart cart) {
        this.cart = cart;
    }

    public String toJson() {
        return JSON.makeObject()
                .put("id", cart.getId().toString())
                .put("total", cart.getTotal())
                .put("items", JSON.makeArray().addItems(
                        cart.getItems(),
                        (item) -> JSON.makeObject()
                                .put("uuid", item.getId())
                                .put("description", item.getDescription())
                                .put("price", item.getPrice())
                                .build())
                        .build())
                .build()
                .toJson();
    }

}

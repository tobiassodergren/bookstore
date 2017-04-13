package org.sodergren.restapi.response;

import org.sodergren.model.entities.Cart;
import org.sodergren.model.entities.CartItemDescription;
import org.sodergren.restapi.json.JSON;

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
                        (item) -> {
                            CartItemDescription description = item.getDescription();
                            return JSON.makeObject()
                                    .put("uuid", item.getId().toString())
                                    .put("title", description.title)
                                    .put("author", description.author)
                                    .put("quantity", description.quantity)
                                    .put("price", item.getPrice())
                                    .build();

                        })
                        .build())
                .build()
                .toJson();
    }
}

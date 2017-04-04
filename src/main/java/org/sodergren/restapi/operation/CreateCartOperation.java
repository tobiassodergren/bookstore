package org.sodergren.restapi.operation;

import org.sodergren.cart.CartRepository;
import org.sodergren.model.entities.Cart;
import org.sodergren.restapi.response.CartResponse;

import javax.ws.rs.core.Response;

public class CreateCartOperation extends OperationBase {

    private final CartRepository cartRepository;

    public CreateCartOperation(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Response execute() {
        Cart cart = this.cartRepository.createCart();
        CartResponse cartResponse = new CartResponse(cart);
        return Response.status(Response.Status.OK).entity(cartResponse.toJson()).build();
    }

}

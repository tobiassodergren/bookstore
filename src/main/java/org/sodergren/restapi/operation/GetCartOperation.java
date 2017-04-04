package org.sodergren.restapi.operation;

import org.sodergren.cart.CartRepository;
import org.sodergren.model.entities.Cart;
import org.sodergren.restapi.response.CartResponse;

import javax.ws.rs.core.Response;
import java.util.UUID;

public class GetCartOperation extends OperationBase {
    private final CartRepository cartRepository;
    private final String cartId;

    public GetCartOperation(CartRepository cartRepository, String cartId) {
        this.cartRepository = cartRepository;
        this.cartId = cartId;
    }

    @Override
    public Response execute() {
        try {
            UUID cartId = UUID.fromString(this.cartId);
            Cart cart = cartRepository.getCartById(cartId);
            CartResponse cartResponse = new CartResponse(cart);
            return Response.status(Response.Status.OK).entity(cartResponse.toJson()).build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(generateErrorBody("Illegal cartId", e))
                    .build();
        }
    }
}

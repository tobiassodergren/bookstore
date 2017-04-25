package org.sodergren.restapi.operation;

import org.sodergren.bookstore.BookStore;
import org.sodergren.cart.CartRepository;
import org.sodergren.model.entity.Book;
import org.sodergren.model.entity.BookList;
import org.sodergren.model.entity.Cart;
import org.sodergren.restapi.response.OrderStatusResponse;

import javax.ws.rs.core.Response;
import java.util.UUID;

public class CheckoutCartOperation extends OperationBase {

    private final CartRepository cartRepository;
    private final BookList bookStore;
    private final String cartId;

    public CheckoutCartOperation(CartRepository cartRepository, BookList bookStore, String cartId) {
        this.cartRepository = cartRepository;
        this.bookStore = bookStore;
        this.cartId = cartId;
    }

    @Override
    public Response execute() {
        UUID cartId = UUID.fromString(this.cartId);

        Cart cart = cartRepository.getCartById(cartId);

        Book[] order = cart.toOrder();

        int[] result = bookStore.buy(order);

        return Response.ok(new OrderStatusResponse(result).toJson()).build();
    }
}

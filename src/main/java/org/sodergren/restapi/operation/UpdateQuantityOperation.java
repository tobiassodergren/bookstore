package org.sodergren.restapi.operation;

import org.sodergren.bookstore.NotFoundException;
import org.sodergren.cart.CartRepository;
import org.sodergren.model.entities.Book;
import org.sodergren.model.entities.BookList;
import org.sodergren.model.entities.Cart;

import javax.ws.rs.core.Response;
import java.util.UUID;

public class UpdateQuantityOperation extends OperationBase {
    private final CartRepository cartRepository;
    private final BookList bookList;
    private final String cartId;
    private final String bookId;
    private final int quantity;

    public UpdateQuantityOperation(CartRepository cartRepository, BookList bookList, String cartId, String bookId, int quantity) {
        this.cartRepository = cartRepository;
        this.bookList = bookList;
        this.cartId = cartId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    @Override
    public Response execute() {
        try {
            UUID cartUUID = convertToUuid(cartId, "cartId");
            UUID bookUUID = convertToUuid(bookId, "bookId");

            Cart cart = cartRepository.getCartById(cartUUID);

            Book book = bookList.getById(bookUUID);

            cart.addItemToList(book, quantity);

        } catch (UUIDException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(generateErrorBody(e.getMessage(), e.getCause()))
                    .build();
        } catch (NotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(generateErrorBody("Problem updating quantity", e))
                    .build();
        }

        return Response.serverError().build();
    }
}

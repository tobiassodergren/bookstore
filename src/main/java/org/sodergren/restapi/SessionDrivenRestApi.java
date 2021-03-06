package org.sodergren.restapi;

import org.glassfish.jersey.server.ResourceConfig;
import org.sodergren.cart.CartRepository;
import org.sodergren.model.entity.BookList;
import org.sodergren.restapi.operation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
public class SessionDrivenRestApi extends ResourceConfig {

    private final CartRepository cartRepository;
    private final BookList bookStore;

    public SessionDrivenRestApi(CartRepository cartRepository, BookList bookList) {
        this.cartRepository = cartRepository;
        this.bookStore = bookList;
    }

    @Path("cart")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInitialCart() {
        return new CreateCartOperation(cartRepository).execute();
    }

    @Path("cart/{cartId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCartForSession(@PathParam("cartId") String cartId) {
        return new GetCartOperation(cartRepository, cartId).execute();
    }

    @Path("cart/{cartId}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response setQuantityForItem(@PathParam("cartId") String cartId, @QueryParam("bookId") String bookId, @QueryParam("quantity") int quantity) {
        return new UpdateCartQuantityOperation(cartRepository, bookStore, cartId, bookId, quantity).execute();
    }

    @Path("cart/{cartId}/checkout")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkout(@PathParam("cartId") String cartId) {
        return new CheckoutCartOperation(cartRepository, bookStore, cartId).execute();
    }

    @Path("books")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchBooks(@QueryParam("query") String query) {
        return new SearchBooksOperation(bookStore, query).execute();
    }

}

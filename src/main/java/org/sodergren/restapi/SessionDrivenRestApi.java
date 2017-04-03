package org.sodergren.restapi;

import org.glassfish.jersey.server.ResourceConfig;
import org.sodergren.cart.CartRepository;
import org.sodergren.model.entities.BookList;
import org.sodergren.model.entities.Cart;
import org.sodergren.restapi.response.CartResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.UUID;

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
    @Produces("application/json")
    public Response getInitialCart() {
        Cart cart = this.cartRepository.getCartById(null);
        return generateCartResponse(cart);
    }

    @Path("cart/{id}")
    @GET
    @Produces("application/json")
    public Response getCartForSession(@PathParam("id") String id) {
        Cart cart = cartRepository.getCartById(UUID.fromString(id));
        return generateCartResponse(cart);
    }

    private Response generateCartResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse(cart);
        return Response.status(Response.Status.OK).entity(cartResponse.toJson()).build();
    }
}

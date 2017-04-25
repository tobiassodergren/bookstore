package org.sodergren.embeddedserver;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.sodergren.cart.CartRepository;
import org.sodergren.model.entity.BookList;
import org.sodergren.restapi.SessionDrivenRestApi;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class JettyServer {

    public JettyServer(int port, CartRepository repo, BookList bookList) throws Exception {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(port)
                .build();

        ResourceConfig cartConfig = new SessionDrivenRestApi(repo, bookList);

        ResourceConfig app = ResourceConfig.forApplication(cartConfig).register(cartConfig);

        Server server = JettyHttpContainerFactory.createServer(baseUri, app,
                false);

        server.start();
    }
}

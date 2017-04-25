package org.sodergren;

import org.sodergren.admin.AdminRepl;
import org.sodergren.bookstore.BookStore;
import org.sodergren.cart.CartRepository;
import org.sodergren.embeddedserver.JettyServer;
import org.sodergren.importer.URLImporter;

import java.net.URL;

/**
 * The main application that initiates a book store, cart repository and optionally imports from a URL
 */
public class App {
    public static void main(String[] args) throws Exception {

        BookStore store = new BookStore();
        CartRepository cartRepository = new CartRepository();

        if (args.length > 0) {
            URLImporter urlImporter = new URLImporter(store, new URL(args[0]));
            urlImporter.execute();
        }

        new JettyServer(5000, cartRepository, store);

        new AdminRepl(cartRepository, store);

    }
}

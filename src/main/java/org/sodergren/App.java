package org.sodergren;

import org.sodergren.bookstore.BookStore;
import org.sodergren.cart.CartRepository;
import org.sodergren.embeddedserver.JettyServer;
import org.sodergren.importer.URLImporter;
import org.sodergren.model.entity.BookList;

import java.net.URL;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        BookList store = new BookStore();
        CartRepository cartRepository = new CartRepository();

        URLImporter urlImporter = new URLImporter(store, new URL("http://www.contribe.se/bookstoredata/bookstoredata.txt"));
        urlImporter.execute();

        JettyServer embeddedServer = new JettyServer(5000, cartRepository, store);

    }
}

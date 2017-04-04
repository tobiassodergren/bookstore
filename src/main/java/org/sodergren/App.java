package org.sodergren;

import org.sodergren.bookstore.BookStore;
import org.sodergren.cart.CartRepository;
import org.sodergren.importer.URLImporter;
import org.sodergren.jerseyserver.EmbeddedServer;
import org.sodergren.model.entities.BookList;

import java.net.URL;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        BookList store = new BookStore();
        URLImporter urlImporter = new URLImporter(store, new URL("http://www.contribe.se/bookstoredata/bookstoredata.txt"));

        urlImporter.execute();

        EmbeddedServer embeddedServer = new EmbeddedServer(5000, new CartRepository(), new BookStore());


    }
}

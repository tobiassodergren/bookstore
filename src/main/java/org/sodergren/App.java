package org.sodergren;

import org.sodergren.bookstore.BookStore;
import org.sodergren.importer.URLImporter;
import org.sodergren.model.entities.BookList;

import java.io.IOException;
import java.net.URL;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        BookList store = new BookStore();
        URLImporter urlImporter = new URLImporter(store, new URL("http://www.contribe.se/bookstoredata/bookstoredata.txt"));
    }
}

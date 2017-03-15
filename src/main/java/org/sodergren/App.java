package org.sodergren;

import org.sodergren.importer.URLImporter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        URLImporter urlImporter = new URLImporter(null, new URL("http://www.contribe.se/bookstoredata/bookstoredata.txt"));
    }
}

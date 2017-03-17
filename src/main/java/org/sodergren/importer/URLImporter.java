package org.sodergren.importer;

import org.sodergren.model.entities.BookList;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class URLImporter {

    public URLImporter(BookList bookList, URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();

        try (InputStream stream = urlConnection.getInputStream()) {

            String bookData = readBookDataFromStream(stream);

            List<StockImport> importedStockInfo = CSVParser.extractBookStockImport(bookData);

            importedStockInfo.forEach((bookStock) -> {
                System.out.println("Successfully imported " + bookStock);
                bookList.add(bookStock.book, bookStock.quantity);
            });
        }

    }

    /**
     * Read all data in string
     *
     * @param stream Stream to read from
     * @return The content as string
     */
    private String readBookDataFromStream(InputStream stream) {
        return new Scanner(stream, "UTF-8").useDelimiter("\\Z").next();
    }


}

package org.sodergren.importer;

import org.sodergren.model.entities.Book;
import org.sodergren.model.entities.BookList;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLImporter {

    public URLImporter(BookList bookList, URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();

        try (InputStream stream = urlConnection.getInputStream()) {

            String bookData = readBookDataFromStream(stream);

            List<StockImport> importedStockInfo = extractBookStockImport(bookData);

            // TODO Log this success in import processing result file

            importedStockInfo.forEach((bookStock) -> {
                System.out.println("Successfully imported " + bookStock);
                bookList.add(bookStock.book, bookStock.quantity);
            });
        }

    }


    private List<StockImport> extractBookStockImport(String bookData) {
        Pattern pattern = Pattern.compile("([^;]+);([^;]+);([^;]+);([^\n]+)\n", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(bookData);

        List<StockImport> result = new ArrayList<>();

        while (matcher.find()) {
            try {
                String title = matcher.group(1);
                String author = matcher.group(2);
                BigDecimal price = parseBigDecimal(matcher.group(3));
                int quantity = parseQuantity(matcher.group(4));

                result.add(new StockImport(new Book(title, author, price), quantity));
            } catch (Exception e) {
                // TODO Log this issue in import processing result file
                System.out.println(String.format("Problem importing title=%s, author=%s, price=%s, quantity=%s", matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
            }
        }

        return result;
    }

    private int parseQuantity(String data) {
        return Integer.parseInt(data);
    }

    private BigDecimal parseBigDecimal(String data) {
        return BigDecimal.valueOf(1);
    }

    /**
     * Read all data in string
     *
     * @param stream
     * @return
     */
    private String readBookDataFromStream(InputStream stream) {
        return new Scanner(stream, "UTF-8").useDelimiter("\\Z").next();
    }


}

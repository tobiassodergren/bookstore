package org.sodergren.importer;

import org.sodergren.model.entity.Book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.sodergren.model.util.ParserUtil.parseBigDecimal;
import static org.sodergren.model.util.ParserUtil.parseQuantity;

/**
 * Responsible for parsing book and corresponding quantities from a string containing CSV values.
 */
class CSVParser {
    static List<StockImport> extractBookStockImport(String bookData) {
        Pattern pattern = Pattern.compile("^([^;]+);([^;]+);([^;]+);([^\n]+)$", Pattern.DOTALL | Pattern.MULTILINE);
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
                System.err.println(String.format("Problem importing title=%s, author=%s, price=%s, quantity=%s", matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
            }
        }

        return result;
    }

}

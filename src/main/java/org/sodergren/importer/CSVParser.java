package org.sodergren.importer;

import org.sodergren.model.entities.Book;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    static int parseQuantity(String data) {
        return Integer.parseInt(data);
    }

    static BigDecimal parseBigDecimal(String data) throws ParseException {
        NumberFormat instance = NumberFormat.getInstance(Locale.US);
        if (!(instance instanceof DecimalFormat)) {
            throw new IllegalStateException("Cannot parse number due to limitation of NumberFormat capabilities");
        }
        DecimalFormat format = (DecimalFormat) instance;
        format.setParseBigDecimal(true);
        return (BigDecimal) format.parse(data);
    }

}

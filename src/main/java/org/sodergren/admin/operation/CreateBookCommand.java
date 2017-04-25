package org.sodergren.admin.operation;

import org.sodergren.admin.Command;
import org.sodergren.bookstore.BookStore;
import org.sodergren.model.entity.Book;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.sodergren.model.util.ParserUtil.parseBigDecimal;

public class CreateBookCommand implements Command {
    private final BookStore bookStore;

    public CreateBookCommand(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    @Override
    public Pattern getLinePattern() {
        return Pattern.compile("create book title\\s*=\\s*\"([^\"]+)\"\\s+author\\s*=\\s*\"([^\"]+)\"\\s+price\\s*=\\s*\"([^\"]+)\"");
    }

    @Override
    public void executeLine(Matcher lineMatcher) {
        String title = lineMatcher.group(1);
        String author = lineMatcher.group(2);

        try {
            BigDecimal price = parseBigDecimal(lineMatcher.group(3));

            System.out.println(String.format("%s - %s : %s",
                    lineMatcher.group(1),
                    lineMatcher.group(2),
                    lineMatcher.group(3)));

            Book book = new Book(title, author, price);

            boolean added = bookStore.add(book, 1);

            if (added) {
                System.out.println("Book added successfully. Quantity is set to 1");
            } else {
                System.out.println("Book already exists, quantity updated with +1");
            }
        } catch (ParseException e) {
            System.err.println("Error parsing price: " + e.getMessage());
        }

    }

    @Override
    public String getHelp() {
        return "create book title=\"...\" author=\"...\" price=\"...\" - creates a book in store";
    }
}

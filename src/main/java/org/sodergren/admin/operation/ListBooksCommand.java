package org.sodergren.admin.operation;

import org.sodergren.admin.Command;
import org.sodergren.bookstore.BookStock;
import org.sodergren.bookstore.BookStore;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListBooksCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("list books");
    private final BookStore bookStore;

    public ListBooksCommand(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    @Override
    public Pattern getLinePattern() {
        return PATTERN;
    }

    @Override
    public void executeLine(Matcher lineMatcher) {
        Collection<BookStock> list = bookStore.getStock();
        for (BookStock stock : list) {
            System.out.println(String.format("%s - author: %s, title: %s, price: %s, quantity: %s",
                    stock.getBook().getId(),
                    stock.getBook().getAuthor(),
                    stock.getBook().getTitle(),
                    stock.getBook().getPrice(),
                    stock.getQuantity()));
        }
    }

    @Override
    public String getHelp() {
        return "list books - list the books available in store";
    }
}

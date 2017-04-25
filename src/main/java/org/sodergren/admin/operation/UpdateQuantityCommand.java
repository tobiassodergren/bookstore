package org.sodergren.admin.operation;

import org.sodergren.admin.Command;
import org.sodergren.bookstore.BookStore;
import org.sodergren.model.entity.Book;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateQuantityCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("update quantity \\s*([^\\s]+)\\s(\\d+)");
    private final BookStore store;

    public UpdateQuantityCommand(BookStore store) {
        this.store = store;
    }

    @Override
    public Pattern getLinePattern() {
        return PATTERN;
    }

    @Override
    public void executeLine(Matcher lineMatcher) {
        String uuid = lineMatcher.group(1);
        String quantity = lineMatcher.group(2);

        try {
            Book book = store.getById(UUID.fromString(uuid));
            store.add(book, Integer.parseInt(quantity));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getHelp() {
        return "update quantity {uuid} {quantity} - update quantity for book specified by uuid";
    }
}

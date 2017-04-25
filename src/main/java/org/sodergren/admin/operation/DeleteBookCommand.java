package org.sodergren.admin.operation;

import org.sodergren.admin.Command;
import org.sodergren.bookstore.BookStore;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteBookCommand implements Command {
    private final BookStore store;

    public DeleteBookCommand(BookStore store) {
        this.store = store;
    }

    @Override
    public Pattern getLinePattern() {
        return Pattern.compile("delete book \\s*(.*)");
    }

    @Override
    public void executeLine(Matcher lineMatcher) {
        String uuid = lineMatcher.group(1);

        store.delete(UUID.fromString(uuid));
    }

    @Override
    public String getHelp() {
        return "delete book {uuid} - Deletes a book from store";
    }
}

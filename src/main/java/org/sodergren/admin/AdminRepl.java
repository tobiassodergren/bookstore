package org.sodergren.admin;

import org.sodergren.admin.operation.ListBooksCommand;
import org.sodergren.admin.operation.ListCartsCommand;
import org.sodergren.admin.operation.PrintHelpCommand;
import org.sodergren.admin.operation.UpdateQuantityCommand;
import org.sodergren.bookstore.BookStore;
import org.sodergren.cart.CartRepository;
import org.sodergren.model.entity.BookList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class AdminRepl {

    private List<Command> operations = new ArrayList<>();

    public AdminRepl(CartRepository cartRepository, BookStore bookStore) throws IOException {

        operations.add(new ListCartsCommand(cartRepository));
        operations.add(new ListBooksCommand(bookStore));
        operations.add(new UpdateQuantityCommand(bookStore));
        operations.add(new PrintHelpCommand(operations));

        printLogo();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("[cmd], 'help' for help   > ");
            String line = reader.readLine().trim();
            operations.forEach(o -> {
                Matcher matcher = o.getLinePattern().matcher(line);
                if (matcher.find()) {
                    o.executeLine(matcher);
                }
            });
        }
    }

    private void printLogo() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("  ____              _          _                 \n")
                .append(" |  _ \\            | |        | |                \n")
                .append(" | |_) | ___   ___ | | __  ___| |_ ___  _ __ ___ \n")
                .append(" |  _ < / _ \\ / _ \\| |/ / / __| __/ _ \\| '__/ _ \\\n")
                .append(" | |_) | (_) | (_) |   <  \\__ \\ || (_) | | |  __/\n")
                .append(" |____/ \\___/ \\___/|_|\\_\\ |___/\\__\\___/|_|  \\___|\n");

        System.out.println(builder.toString());
    }
}

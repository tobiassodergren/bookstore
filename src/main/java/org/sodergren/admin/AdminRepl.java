package org.sodergren.admin;

import org.sodergren.admin.operation.*;
import org.sodergren.bookstore.BookStore;
import org.sodergren.cart.CartRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class AdminRepl {

    public AdminRepl(CartRepository cartRepository, BookStore bookStore) throws IOException {

        List<Command> operations = new ArrayList<>();

        operations.add(new ListCartsCommand(cartRepository));
        operations.add(new ListBooksCommand(bookStore));
        operations.add(new CreateBookCommand(bookStore));
        operations.add(new UpdateQuantityCommand(bookStore));
        operations.add(new DeleteBookCommand(bookStore));
        operations.add(new PrintHelpCommand(operations));

        printLogo();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.print("[Enter cmd], 'help' for help > ");
                String line = reader.readLine().trim();
                operations.forEach(o -> {
                    Matcher matcher = o.getLinePattern().matcher(line);
                    if (matcher.find()) {
                        o.executeLine(matcher);
                    }
                });
            } catch (Exception e) {
                System.err.println("Error caught: " + e.getMessage());
            }
        }
    }

    private void printLogo() {
        System.out.println("  ____              _          _                 ");
        System.out.println(" |  _ \\            | |        | |                ");
        System.out.println(" | |_) | ___   ___ | | __  ___| |_ ___  _ __ ___ ");
        System.out.println(" |  _ < / _ \\ / _ \\| |/ / / __| __/ _ \\| '__/ _ \\");
        System.out.println(" | |_) | (_) | (_) |   <  \\__ \\ || (_) | | |  __/");
        System.out.println(" |____/ \\___/ \\___/|_|\\_\\ |___/\\__\\___/|_|  \\___|");
        System.out.println("");
    }
}

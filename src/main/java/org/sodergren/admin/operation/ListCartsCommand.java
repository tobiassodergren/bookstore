package org.sodergren.admin.operation;

import org.sodergren.admin.Command;
import org.sodergren.cart.CartRepository;
import org.sodergren.cart.ServerCart;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCartsCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("list carts");
    private final CartRepository repository;

    public ListCartsCommand(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeLine(Matcher lineMatcher) {
        Collection<ServerCart> carts = repository.getCarts();
        StringBuilder builder = new StringBuilder();
        carts.forEach(c -> {
            System.out.println(String.format("%s - number of items: %d, total: %s",
                    c.getId(),
                    c.getItems().size(),
                    c.getTotal()));
        });

    }

    @Override
    public Pattern getLinePattern() {
        return PATTERN;
    }


    public String getHelp() {
        return "list carts - list the active carts";
    }
}

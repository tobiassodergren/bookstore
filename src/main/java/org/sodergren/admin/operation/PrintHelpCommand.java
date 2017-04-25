package org.sodergren.admin.operation;

import org.sodergren.admin.Command;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintHelpCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("help");

    private final List<Command> operations;

    public PrintHelpCommand(List<Command> operations) {

        this.operations = operations;
    }

    @Override
    public Pattern getLinePattern() {
        return PATTERN;
    }

    @Override
    public void executeLine(Matcher matcher) {
        operations.forEach(o -> System.out.println(o.getHelp()));
    }

    @Override
    public String getHelp() {
        return "help - Prints help";
    }
}

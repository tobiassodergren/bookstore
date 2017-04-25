package org.sodergren.admin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {

    Pattern getLinePattern();

    void executeLine(Matcher lineMatcher);

    String getHelp();
}

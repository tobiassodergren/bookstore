package org.sodergren.importer;

import org.sodergren.model.entities.BookList;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLImporter {
    public URLImporter(BookList bookList, URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        try (InputStream stream = urlConnection.getInputStream()) {
            String result = new Scanner(stream, "UTF-8").useDelimiter("\\Z").next();

            Pattern pattern = Pattern.compile("([^;]+);([^;]+);([^;]+);([^\n]+)\n", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(result);
            while(matcher.find()) {
                System.out.println("matcher.group(1) = " + matcher.group(1));
                System.out.println("matcher.group(2) = " + matcher.group(2));
                System.out.println("matcher.group(3) = " + matcher.group(3));
                System.out.println("matcher.group(4) = " + matcher.group(4));
            }
        }

    }
}

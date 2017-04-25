package org.sodergren.importer;

import org.junit.Test;
import org.sodergren.model.entity.Book;
import org.sodergren.model.util.ParserUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class CSVParserTest {

    private static final String FIXTURE = "Mastering åäö;Average Swede;762.00;15\n" +
            "How To Spend Money;Rich Bloke;1,000,000.00;1\n" +
            "Generic Title;First Author;185.50;5\n" +
            "Generic Title;Second Author;1,748.00;3\n" +
            "Random Sales;Cunning Bastard;999.00;20\n" +
            "Random Sales;Cunning Bastard;499.50;3\n" +
            "Desired;Rich Bloke;564.50;0\n";

    private static final String SOME_INVALID_FIXTURE = "Mastering åäö;Average Swede;X762.00;15\n" +
            "How To Spend Money;Rich Bloke;1,000,000.00;1\n" +
            "Generic Title;First Author;185.50;5\n" +
            "Generic Title;Second Author;1,748.00;3\n" +
            "Random Sales;Cunning Bastard;;20\n" +
            "Random Sales;Cunning Bastard;499.50;Y\n" +
            "Desired;Rich Bloke;564.50;0\n";

    @Test
    public void shouldHandlePrice() throws ParseException {
        assertThat(ParserUtil.parseBigDecimal("1,748.50"), equalTo(BigDecimal.valueOf(174850, 2)));
    }

    @Test
    public void shouldParseFixture() throws ParseException {
        List<StockImport> result = CSVParser.extractBookStockImport(FIXTURE);

        assertThat(result.size(), equalTo(7));

        assertThat(result, hasItems(
                new StockImport(new Book("Mastering åäö", "Average Swede", ParserUtil.parseBigDecimal("762.00")), 15),
                new StockImport(new Book("How To Spend Money", "Rich Bloke", ParserUtil.parseBigDecimal("1,000,000.00")), 1),
                new StockImport(new Book("Generic Title", "First Author", ParserUtil.parseBigDecimal("185.50")), 5),
                new StockImport(new Book("Generic Title", "Second Author", ParserUtil.parseBigDecimal("1,748.00")), 3),
                new StockImport(new Book("Random Sales", "Cunning Bastard", ParserUtil.parseBigDecimal("999.00")), 20),
                new StockImport(new Book("Random Sales", "Cunning Bastard", ParserUtil.parseBigDecimal("499.50")), 3),
                new StockImport(new Book("Desired", "Rich Bloke", ParserUtil.parseBigDecimal("564.50")), 0))
        );
    }

    @Test
    public void shouldParseTheValidItems() throws ParseException {
        List<StockImport> result = CSVParser.extractBookStockImport(SOME_INVALID_FIXTURE);

        assertThat(result.size(), equalTo(4));

        assertThat(result, hasItems(
                new StockImport(new Book("How To Spend Money", "Rich Bloke", ParserUtil.parseBigDecimal("1,000,000.00")), 1),
                new StockImport(new Book("Generic Title", "First Author", ParserUtil.parseBigDecimal("185.50")), 5),
                new StockImport(new Book("Generic Title", "Second Author", ParserUtil.parseBigDecimal("1,748.00")), 3),
                new StockImport(new Book("Desired", "Rich Bloke", ParserUtil.parseBigDecimal("564.50")), 0))
        );

    }
}

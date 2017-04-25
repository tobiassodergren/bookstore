package org.sodergren.model.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ParserUtil {
    public static int parseQuantity(String data) {
        return Integer.parseInt(data);
    }

    public static BigDecimal parseBigDecimal(String data) throws ParseException {
        NumberFormat instance = NumberFormat.getInstance(Locale.US);
        if (!(instance instanceof DecimalFormat)) {
            throw new IllegalStateException("Cannot parse number due to limitation of NumberFormat capabilities");
        }
        DecimalFormat format = (DecimalFormat) instance;
        format.setParseBigDecimal(true);
        return (BigDecimal) format.parse(data);
    }
}

package org.dhltest.framework.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.runner.Description;

/**
 * Has utils for working with Strings
 */
public class StringUtils {
    private StringUtils() {
    }

    /**
     * Sometimes Edge adds to string Unicode zero width space at beginning of a string
     * Because of this some tests are failed because this symbol is not expected
     *
     * @param text changed text
     * @return text without Unicode zero width space
     */
    public static String removeUnicodeZeroWidthSpace(String text) {
        return text.replace("\u200B", "");
    }

    /**
     * Converts string to Date object like "yyyy-MM-dd HH:mm"
     *
     * @param text string for converting
     * @return Date object
     */
    public static LocalDateTime convertToDate(String text) {
        return convertToDate(text, "yyyy-MM-dd HH:mm");
    }

    /**
     * Converts string to Date object according to pattern
     *
     * @param text string for converting
     * @param pattern Date pattern string
     * @return Date object
     */
    public static LocalDateTime convertToDate(String text, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

        dateTimeFormatter = new DateTimeFormatterBuilder().append(dateTimeFormatter)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();

        return LocalDateTime.parse(text, dateTimeFormatter);
    }

    /**
     * Gets positive integer number from string
     *
     * @param text text for number search
     * @return number or -1 if number was not found
     */
    public static int getNumberFromText(String text) {
        return Integer.parseInt(parseString(text, "\\D*(\\d+)\\D*"));
    }

    /**
     * Gets positive double number from string
     *
     * @param text text for number search
     * @return number or -1 if number was not found
     */
    public static double getDoubleFromText(String text) {
        return convertToDouble(parseString(text, "\\D*(\\d+\\.*\\d*)\\D*"));
    }

    /**
     * Returns true if string has digital values only "40" - true; "40S" - false
     *
     * @param s string to test
     * @return true is string is number otherwise false
     */
    public static boolean isInteger(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), 10) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param str checked string
     * @return true if string is numeric otherwise - false
     */
    public static boolean isNumeric(String str) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    public static String urlEncode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

    private static void append(StringBuilder builder, String string, int index) {
        if (0 < index) {
            builder.append(",");
        }
        builder.append(string);
    }

    public static double convertToDouble(String str) {
        if (isNumeric(str)) {
            return Double.parseDouble(str);
        }

        throw new IllegalArgumentException(str + " cannot be converted to double value");
    }

    public static String unixPath(String first, String... more) {
        return Paths.get(first, more).toString().replace("\\", "/");
    }

    public static String methodNameFrom(Description description) {
        String methodName = description.getMethodName();
        if (methodName.contains("[")) {
            methodName = methodName.substring(0, methodName.indexOf('['));
        }
        return methodName.trim();
    }

    private static String parseString(String text, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(text);

        if (!matcher.matches() || 0 == matcher.groupCount()) {
            return "-1";
        }

        return matcher.group(1);
    }
}

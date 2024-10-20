package org.dhltest.framework.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Has utils for working with Dates
 */
public class DateUtils {
    private DateUtils() {
    }

    /**
     * Converts Date object to string like "yyyy-MM-dd"
     *
     * @param date for converting
     * @return String object
     */
    public static String convertToString(LocalDateTime date) {
        return convertToString(date, "yyyy-MM-dd");
    }

    /**
     * Converts Date object to string according to pattern
     *
     * @param date for converting
     * @param pattern pattern string
     * @return String object
     */
    public static String convertToString(LocalDateTime date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern, Locale.US));
    }
}

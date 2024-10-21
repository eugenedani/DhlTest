package org.dhltest.framework.util;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import org.junit.runner.Description;

/**
 * Has utils for working with Strings
 */
public class StringUtils {
    private StringUtils() {
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

    public static String methodNameFrom(Description description) {
        String methodName = description.getMethodName();
        if (methodName.contains("[")) {
            methodName = methodName.substring(0, methodName.indexOf('['));
        }
        return methodName.trim();
    }
}

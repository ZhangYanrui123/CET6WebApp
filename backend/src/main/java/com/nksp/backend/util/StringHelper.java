package com.nksp.backend.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
    public static String getValue(String s, String key) {
        String patternString = key + "=([^,}]+)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String[] getValues(String s, String key) {
        String patternString = key + "=([^,}]+)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(s);

        String values = "";
        while (matcher.find()) {
            values += matcher.group(1) + ",";
        }
        if (values.length() > 0) {
            values = values.substring(0, values.length() - 1);
        }

        return values.split(",");
    }
}

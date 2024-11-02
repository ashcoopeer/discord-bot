package com.ashcoopeer.discord.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {
    private static final Pattern pattern1 = Pattern.compile("\\!([A-Za-z]{4,25}) ([A-Za-z]{4,50}) ([A-Za-z]{4,50})");;
    private static final Pattern pattern2 = Pattern.compile("\\!([A-Za-z]{4,25}) ([A-Za-z]{4,50})");

    public static String[] getCommandAndParameters(String message) {
        Matcher matcher;
        try {
            matcher = pattern1.matcher(message);
            if (matcher.find()) {
                return new String[] {matcher.group(1), matcher.group(2), matcher.group(3)};
            } else {
                matcher = pattern2.matcher(message);
                if (matcher.find()) {
                    return new String[] {matcher.group(1), matcher.group(2)};
                }
            }
        } catch (Exception e) {
            return new String[]{};
        } finally {
            matcher = null;
        }
        return new String[]{};
    }

    public static void main(String[] args) {

    }
}

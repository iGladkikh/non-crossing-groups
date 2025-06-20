package com.igladkikh.util;

public class FragmentUtil {

    private FragmentUtil() {
    }

    public static boolean isCorrect(String fragment) {
        String fragmentRegex = "\"?[\\d.]*\"?";
        return fragment.matches(fragmentRegex);
    }

    public static boolean isEmpty(String fragment) {
        return fragment.isEmpty() || fragment.equals("\"\"");
    }

    public static String removeQuotes(String fragment) {
        return fragment.replace("\"", "");
    }

}

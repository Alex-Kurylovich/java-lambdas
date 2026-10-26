package com.insightfullogic.java8.book.chapter7.statics;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Testing {

    private static final Logger logger = Logger.getLogger("Testing");

    public static List<String> allToUpperCase(List<String> words) {
        return words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.<String>toList());
    }

    public static List<String> elementFirstToUpperCaseLambdas(List<String> words) {
        return words.stream()
                .map(Testing::firstToUppercase)
                .collect(Collectors.<String>toList());
    }

    public static String firstToUppercase(String value) { // <1>
        char firstChar = Character.toUpperCase(value.charAt(0));
        return firstChar + value.substring(1);
    }

}

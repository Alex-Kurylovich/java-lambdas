package com.insightfullogic.java8.book.chapter7;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Testing {

    private static final Logger logger = Logger.getLogger("Testing");

    // BEGIN to_uppercase
    public static List<String> allToUpperCase(List<String> words) {
        return words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.<String>toList());
    }
    // END to_uppercase

    // BEGIN firstToUpperCase_lambda
    public static List<String> elementFirstToUpperCaseLambdas(List<String> words) {
        // <1>
        return words.stream()
                .map(Testing::firstToUppercase)
                .collect(Collectors.<String>toList());
    }
    // END firstToUpperCase_lambda

    public static String firstToUppercase(String value) { // <1>
        char firstChar = Character.toUpperCase(value.charAt(0));
        return firstChar + value.substring(1);
    }
    // END firstToUpperCase_reference

}

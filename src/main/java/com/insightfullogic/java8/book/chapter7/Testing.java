package com.insightfullogic.java8.book.chapter7;

import com.insightfullogic.java8.book.chapter1.Album;
import com.insightfullogic.java8.book.chapter1.Artist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    // BEGIN firstToUpperCase_reference
    public static List<String> elementFirstToUppercase(List<String> words) {
        return words.stream()
                .map(Testing::firstToUppercase)
                .collect(Collectors.<String>toList());
    }

    public static String firstToUppercase(String value) { // <1>
        char firstChar = Character.toUpperCase(value.charAt(0));
        return firstChar + value.substring(1);
    }
    // END firstToUpperCase_reference

    public static Set<String> imperativeNationalityReport(Album album) {
        // BEGIN imperative_nationality_report
        Set<String> nationalities = new HashSet<>();
        for (Artist artist : album.getMusicianList()) {
            if (artist.getName().startsWith("The")) {
                String nationality = artist.getNationality();
                System.out.println("Found nationality: " + nationality);
                nationalities.add(nationality);
            }
        }
        return nationalities;
        // END imperative_nationality_report
    }

    public static Set<String> forEachLoggingFailure(Album album) {
        // BEGIN foreach_logging_nationality_report
        album.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(Artist::getNationality)
                .forEach(nationality -> System.out.println("Found: " + nationality));

        // END foreach_logging_nationality_report
        return album.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(Artist::getNationality)
                .collect(Collectors.<String>toSet());
    }

    public static Set<String> nationalityReportUsingPeek(Album album) {
        // BEGIN peek_nationality_report
        // END peek_nationality_report
        return album.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(Artist::getNationality)
                .peek(nation -> System.out.println("Found nationality: " + nation))
                .collect(Collectors.<String>toSet());
    }

}

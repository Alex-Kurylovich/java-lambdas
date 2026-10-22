package com.insightfullogic.java8.book.chapter5;

import com.insightfullogic.java8.book.chapter1.Artist;

import java.util.List;
import java.util.stream.Collectors;

public class StringExamples {

    public static String formatArtists(List<Artist> artists) {
        // BEGIN collectors_joining
        // END collectors_joining
        return artists.stream()
                .map(Artist::getName)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static String formatArtistsForLoop(List<Artist> artists) {
        // BEGIN for_loop
        StringBuilder builder = new StringBuilder("[");
        for (Artist artist : artists) {
            if (builder.length() > 1)
                builder.append(", ");

            String name = artist.getName();
            builder.append(name);
        }
        builder.append("]");
        // END for_loop
        return builder.toString();
    }

    public static String formatArtistsRefactor1(List<Artist> artists) {
        // BEGIN refactor_1
        StringBuilder builder = new StringBuilder("[");
        artists.stream()
                .map(Artist::getName)
                .forEach(name -> {
                    if (builder.length() > 1)
                        builder.append(", ");

                    builder.append(name);
                });
        builder.append("]");
        // END refactor_1
        return builder.toString();
    }

    public static String formatArtistsRefactor2(List<Artist> artists) {
        // BEGIN refactor_2
        StringBuilder reduced =
                artists.stream()
                        .map(Artist::getName)
                        .reduce(new StringBuilder(), (builder, name) -> {
                            if (builder.length() > 0)
                                builder.append(", ");

                            builder.append(name);
                            return builder;
                        }, StringBuilder::append);

        reduced.insert(0, "[");
        reduced.append("]");
        // END refactor_2
        return reduced.toString();
    }

    public static String formatArtistsRefactor3(List<Artist> artists) {
        // BEGIN refactor_3
        StringCombiner combined =
                artists.stream()
                        .map(Artist::getName)
                        .reduce(new StringCombiner(", ", "[", "]"),
                                StringCombiner::add,
                                StringCombiner::merge);

        // END refactor_3
        return combined.toString();
    }

    public static String formatArtistsRefactor4(List<Artist> artists) {
        // BEGIN refactor_4
        // END refactor_4
        return artists.stream()
                .map(Artist::getName)
                .reduce(new StringCombiner(", ", "[", "]"),
                        StringCombiner::add,
                        StringCombiner::merge)
                .toString();
    }

    public static String formatArtistsRefactor5(List<Artist> artists) {
        // BEGIN refactor_5
        // END refactor_5
        return artists.stream()
                .map(Artist::getName)
                .collect(new StringCollector(", ", "[", "]"));
    }

    public static String formatArtistsReducing(List<Artist> artists) {
        // BEGIN reducing
        // END reducing
        return artists.stream()
                .map(Artist::getName).map(name -> new StringCombiner(", ", "[", "]").add(name)).reduce(new StringCombiner(", ", "[", "]"), StringCombiner::merge)
                .toString();
    }
}

package com.insightfullogic.java8.book.chapter5.statics;

import com.insightfullogic.java8.book.chapter5.classes.StringCollector;
import com.insightfullogic.java8.book.chapter5.classes.StringCombiner;
import com.insightfullogic.java8.book.domain.Artist;

import java.util.List;
import java.util.stream.Collectors;

public class StringExamples {

    public static String formatArtists(List<Artist> artists) {
        return artists.stream()
                .map(Artist::getName)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static String formatArtistsForLoop(List<Artist> artists) {
        StringBuilder builder = new StringBuilder("[");
        for (Artist artist : artists) {
            if (builder.length() > 1)
                builder.append(", ");

            String name = artist.getName();
            builder.append(name);
        }
        builder.append("]");
        return builder.toString();
    }

    public static String formatArtistsRefactor1(List<Artist> artists) {
        StringBuilder builder = new StringBuilder("[");
        artists.stream()
                .map(Artist::getName)
                .forEach(name -> {
                    if (builder.length() > 1)
                        builder.append(", ");

                    builder.append(name);
                });
        builder.append("]");
        return builder.toString();
    }

    public static String formatArtistsRefactor2(List<Artist> artists) {
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
        return reduced.toString();
    }

    public static String formatArtistsRefactor3(List<Artist> artists) {
        StringCombiner combined =
                artists.stream()
                        .map(Artist::getName)
                        .reduce(new StringCombiner(", ", "[", "]"),
                                StringCombiner::add,
                                StringCombiner::merge);

        return combined.toString();
    }

    public static String formatArtistsRefactor4(List<Artist> artists) {
        return artists.stream()
                .map(Artist::getName)
                .reduce(new StringCombiner(", ", "[", "]"),
                        StringCombiner::add,
                        StringCombiner::merge)
                .toString();
    }

    public static String formatArtistsRefactor5(List<Artist> artists) {
        return artists.stream()
                .map(Artist::getName)
                .collect(new StringCollector(", ", "[", "]"));
    }

}

package com.insightfullogic.java8.book.chapter5;

import com.insightfullogic.java8.book.domain.Artist;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class LongestName {

    private static final Comparator<Artist> byNameLength = comparing(artist -> artist.getName().length());

    public static Artist byReduce(List<Artist> artists) {
        return artists.stream()
                      .reduce((acc, artist) -> {
                          return (byNameLength.compare(acc, artist) >= 0) ? acc : artist;
                      })
                      .orElseThrow(RuntimeException::new);
    }

    public static Artist byCollecting(List<Artist> artists) {
        return artists.stream()
                      .max(byNameLength)
                      .orElseThrow(RuntimeException::new);
    }

}

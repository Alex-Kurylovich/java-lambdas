package com.insightfullogic.java8.book.chapter3;

import com.insightfullogic.java8.book.domain.Artist;

import java.util.List;
import java.util.stream.Stream;

public class Iteration {

    public void filterArtistsFromLondonPrinted(List<Artist> allArtists) {
        Stream<Artist> london = allArtists.stream()
                .filter(artist -> artist.isFrom("London"));
    }

    public void internalCountArtistsFromLondonPrinted(List<Artist> allArtists) {
        long london = allArtists.stream()
                .filter(artist -> artist.isFrom("London"))
                .count();
    }

}

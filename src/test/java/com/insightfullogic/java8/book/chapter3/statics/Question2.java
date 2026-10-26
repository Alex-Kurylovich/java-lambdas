package com.insightfullogic.java8.book.chapter3.statics;

import com.insightfullogic.java8.book.domain.Artist;

import java.util.List;

public class Question2 {
    // Q3
    public static int countBandMembersInternal(List<Artist> artists) {
        // NB: readers haven't learnt about primitives yet, so can't use the sum() method
        return artists.stream()
                       .map(artist -> artist.getMembers().count())
                       .reduce(0L, Long::sum)
                       .intValue();
    }
}

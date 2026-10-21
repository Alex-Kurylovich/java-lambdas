package com.insightfullogic.java8.book.chapter4;

import com.insightfullogic.java8.book.chapter1.Artist;

import java.util.stream.Stream;

/** A Performance by some musicians - e.g., an Album or Gig. */
public interface Performance {

    public String getName();

    public Stream<Artist> getMusicians();

}
package com.insightfullogic.java8.book.chapter9;

import com.insightfullogic.java8.book.chapter9.classes.BlockingArtistAnalyzer;
import com.insightfullogic.java8.book.chapter9.classes.FakeLookupService;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestChapter9BlockingArtistAnalyzer {

    private final BlockingArtistAnalyzer analyser = new BlockingArtistAnalyzer(new FakeLookupService()::lookupArtistName);

    @Test
    public void largerGroupsAreLarger() {
        assertTrue(analyser.isLargerGroup("The Beatles", "John Coltrane"));
    }

    @Test
    public void smallerGroupsArentLarger() {
        assertFalse(analyser.isLargerGroup("John Coltrane", "The Beatles"));
    }

}

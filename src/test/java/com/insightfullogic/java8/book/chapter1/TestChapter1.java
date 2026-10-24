package com.insightfullogic.java8.book.chapter1;

import com.insightfullogic.java8.book.domain.Album;
import com.insightfullogic.java8.book.domain.Artist;
import com.insightfullogic.java8.book.domain.SampleData;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.insightfullogic.java8.book.domain.SampleData.theBeatles;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;

public class TestChapter1 {

    @Test
    public void allMembers() {
        Album album = new Album("foo", Collections.emptyList(), singletonList(theBeatles));
        Set<Artist> musicians = album.getAllMusicians().collect(toSet());
        Set<Artist> expectedMusicians = new HashSet<>(SampleData.membersOfTheBeatles);
        expectedMusicians.add(theBeatles);
        assertEquals(expectedMusicians, musicians);
    }

}

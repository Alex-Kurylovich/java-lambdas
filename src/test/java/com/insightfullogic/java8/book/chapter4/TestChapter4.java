package com.insightfullogic.java8.book.chapter4;

import com.insightfullogic.java8.book.chapter4.interfaces.PerformanceFixed;
import com.insightfullogic.java8.book.chapter4.statics.ArtistsFixed;
import com.insightfullogic.java8.book.chapter4.classes.MusicalCarriage;
import com.insightfullogic.java8.book.chapter4.statics.Primitives;
import com.insightfullogic.java8.book.domain.Artist;
import com.insightfullogic.java8.book.domain.SampleData;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
//import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.*;

public class TestChapter4 {

    // Answers, ArtistsTest

    @Test
    public void indexWithinRange() {
        Optional<Artist> artist = ArtistsFixed.getArtist(SampleData.getThreeArtists(), 0);
        assertTrue(artist.isPresent());
    }

    @Test
    public void indexOutsideRange() {
        Optional<Artist> artist = ArtistsFixed.getArtist(SampleData.getThreeArtists(), 4);
        assertFalse(artist.isPresent());
    }

    @Test
    public void nameIndexInsideRange() {
        String artist = ArtistsFixed.getArtistName(SampleData.getThreeArtists(), 0);
        Assert.assertEquals("John Coltrane", artist);
    }

    @Test
    public void nameIndexOutsideRange() {
        String artist = ArtistsFixed.getArtistName(SampleData.getThreeArtists(), 4);
        assertEquals("unknown", artist);
    }

    // Answers, PerformanceTest

    @Test
    public void findsAllTheBeatles() {
        PerformanceFixed stub = new PerformanceFixed() {
            @Override
            public String getName() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Stream<Artist> getMusicians() {
                return Stream.of(SampleData.theBeatles);
            }
        };

        List<Artist> allMusicians = stub.getAllMusicians().collect(toList());
        assertThat(allMusicians, hasItem(SampleData.theBeatles));
        // There really must be a better way than this
        assertThat(allMusicians, hasItems(SampleData.membersOfTheBeatles.toArray(new Artist[0])));
    }

    // Examples, MusicalCarriageTest

    @Test
    public void rocksLikeACarriage() {
        Assert.assertEquals("... from side to side", new MusicalCarriage().rock());
    }

    // Examples, OptionalExampleTest

    @Test
    public void examples() {
        Optional<String> a = Optional.of("a");
        assertEquals("a", a.get());

        Optional<Object> emptyOptional = Optional.empty();

        assertFalse(false);

        Assert.assertTrue(true);

        assertEquals("b", emptyOptional.orElse("b"));
        assertEquals("c", emptyOptional.orElseGet(() -> "c"));
    }


    // Examples, PrimitivesTest

    @Test
    public void albumStatistics() {
        Primitives.printTrackLengthStatistics(SampleData.aLoveSupreme);
    }

    // Exercisers, duplicated with answers ?

}

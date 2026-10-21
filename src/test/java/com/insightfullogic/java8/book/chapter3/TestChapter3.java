package com.insightfullogic.java8.book.chapter3;

import com.insightfullogic.java8.book.chapter1.Album;
import com.insightfullogic.java8.book.chapter1.SampleData;
import com.insightfullogic.java8.book.chapter1.Track;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.insightfullogic.java8.book.chapter3.Question2.countBandMembersInternal;
import static com.insightfullogic.java8.book.chapter1.SampleData.*;
import static com.insightfullogic.java8.book.chapter1.SampleData.aLoveSupreme;
import static com.insightfullogic.java8.book.chapter1.SampleData.sampleShortAlbum;
import static com.insightfullogic.java8.book.chapter3.StreamExercises.countBandMembersExternal;
import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TestChapter3 {

    // Answers, FilterUsingReduceTest

    @Test
    public void emptyList1() {
        assertFiltered(x -> false, Collections.<Object>emptyList(), Collections.<Object>emptyList());
    }

    @Test
    public void trueReturnsEverything() {
        assertFiltered((Integer x) -> true, asList(1, 2, 3), asList(1, 2, 3));
    }

    @Test
    public void falseRemovesEverything() {
        assertFiltered((Integer x) -> false, asList(1, 2, 3), Collections.emptyList());
    }

    @Test
    public void filterPartOfList() {
        assertFiltered((Integer x) -> x > 2, asList(1, 2, 3), Collections.singletonList(3));
    }

    private <I> void assertFiltered(Predicate<I> predicate, List<I> input, List<I> expectedOutput) {
        List<I> output = FilterUsingReduce.filter(input.stream(), predicate);
        assertEquals(expectedOutput, output);

        List<I> parallelOutput = FilterUsingReduce.filter(input.parallelStream(), predicate);
        assertEquals(expectedOutput, parallelOutput);
    }

    // Answers, MapUsingReduceTest

    @Test
    public void emptyList2() {
        assertMapped(Function.<Object>identity(), Collections.<Object>emptyList(), Collections.<Object>emptyList());
    }

    @Test
    public void identityMapsToItself() {
        assertMapped((Integer x) -> x, asList(1, 2, 3), asList(1, 2, 3));
    }

    @Test
    public void incrementingNumbers() {
        assertMapped((Integer x) -> x + 2, asList(1, 2, 3), asList(3, 4, 5));
    }

    private <I, O> void assertMapped(Function<I, O> mapper, List<I> input, List<O> expectedOutput) {
        List<O> output = MapUsingReduce.map(input.stream(), mapper);
        assertEquals(expectedOutput, output);

        List<O> parallelOutput = MapUsingReduce.map(input.parallelStream(), mapper);
        assertEquals(expectedOutput, parallelOutput);
    }

    // Answers, Question1Test

    @Test
    public void addsEmptyList() {
        int result = com.insightfullogic.java8.book.chapter3.Question1.addUp(Stream.empty());
        assertEquals(0, result);
    }

    @Test
    public void addsListWithValues() {
        int result = com.insightfullogic.java8.book.chapter3.Question1.addUp(Stream.of(1, 3, -2));
        assertEquals(2, result);
    }

    @Test
    public void extractsNamesAndOriginsOfArtists() {
        List<String> namesAndOrigins = com.insightfullogic.java8.book.chapter3.Question1.getNamesAndOrigins(SampleData.getThreeArtists());
        assertEquals(asList("John Coltrane", "US", "John Lennon", "UK", "The Beatles", "UK"), namesAndOrigins);
    }

    @Test
    public void findsShortAlbums() {
        List<Album> input = asList(manyTrackAlbum, sampleShortAlbum, aLoveSupreme);
        List<Album> result = Question1.getAlbumsWithAtMostThreeTracks(input);
        assertEquals(asList(sampleShortAlbum, aLoveSupreme), result);
    }

    // Answers, Question2Test

    @Test
    public void internal() {
        assertEquals(4, countBandMembersInternal(Arrays.asList(SampleData.johnColtrane, SampleData.theBeatles)));
    }

    // Answers, StringExercisesTest

    @Test
    public void noLowercaseLettersInAnEmptyString() {
        assertEquals(0, com.insightfullogic.java8.book.chapter3.StringExercises.countLowercaseLetters(""));
    }

    @Test
    public void countsLowercaseLetterExample() {
        assertEquals(3, com.insightfullogic.java8.book.chapter3.StringExercises.countLowercaseLetters("aBcDeF"));
    }

    @Test
    public void supportsNoLowercaseLetters() {
        assertEquals(0, com.insightfullogic.java8.book.chapter3.StringExercises.countLowercaseLetters("ABCDEF"));
    }

    @Test
    public void noStringReturnedForEmptyList() {
        assertFalse(com.insightfullogic.java8.book.chapter3.StringExercises.mostLowercaseString(Collections.<String>emptyList()).isPresent());
    }

    @Test
    public void findsMostLowercaseString() {
        Optional<String> result = StringExercises.mostLowercaseString(Arrays.asList("a", "abc", "ABCde"));
        assertEquals(Optional.of("abc"), result);
    }

    // Examples, HigherOrderFunctionExamplesTest

    @Test
    public void collectToList() {
        // BEGIN collect_to_list_1
        List<String> collected = Stream.of("a", "b", "c") // <1>
                .collect(Collectors.toList()); // <2>

        assertEquals(Arrays.asList("a", "b", "c"), collected); // <3>
        // END collect_to_list_1
    }

    @Test
    public void mapToUpperCase() {
        // BEGIN map_to_uppercase
        List<String> collected = Stream.of("a", "b", "hello")
                .map(String::toUpperCase) // <1>
                .collect(toList());

        assertEquals(asList("A", "B", "HELLO"), collected);
        // END map_to_uppercase
    }

    @Test
    public void forToUpperCase() {
        // BEGIN for_to_uppercase
        List<String> collected = new ArrayList<>();
        for (String string : asList("a", "b", "hello")) {
            String uppercaseString = string.toUpperCase();
            collected.add(uppercaseString);
        }

        assertEquals(asList("A", "B", "HELLO"), collected);
        // END for_to_uppercase
    }

    @Test
    public void imperativeMaxLength() {
        // BEGIN imperativeMaxLength
        List<Track> tracks = asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        Track shortestTrack = tracks.get(0);
        for (Track track : tracks) {
            if (track.getLength() < shortestTrack.getLength()) {
                shortestTrack = track;
            }
        }

        assertEquals(tracks.get(1), shortestTrack);
        // END imperativeMaxLength
    }

    @Test
    public void streamsMaxLength() {
        // BEGIN streamsMaxLength
        List<Track> tracks = asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(Track::getLength))
                .get();

        assertEquals(tracks.get(1), shortestTrack);
        // END streamsMaxLength
    }

    @Test
    public void streamsAnyMatch() {
        // BEGIN streamsAnyMatch
        List<Track> tracksOnColtrane = asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        boolean matchLength = tracksOnColtrane.stream()
                .anyMatch(track -> track.getLength() > 500);
        assertTrue(matchLength);
        // END streamsAnyMatch
    }

    @Test
    public void imperativeAnyMatch() {
        // BEGIN imperativeAnyMatch
        List<Track> tracksOnColtrane = asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        boolean matchLength = false;
        for(Track track : tracksOnColtrane) {
            if (track.getLength() > 500) {
                matchLength = true;
                break;
            }
        }

        assertTrue(matchLength);
        // END imperativeAnyMatch
    }

    @Test
    public void sumUsingReduce() {
        // BEGIN count_using_reduce
        int count = Stream.of(1, 2, 3)
                .reduce(0, Integer::sum);

        assertEquals(6, count);
        // END count_using_reduce
    }

    @Test
    public void expandedReduce() {
        // BEGIN expanded_reduce
        BinaryOperator<Integer> accumulator = Integer::sum;
        int count = accumulator.apply(
                accumulator.apply(
                        accumulator.apply(0, 1),
                        2),
                3);
        // END expanded_reduce

        assertEquals(6, count);
    }

    @Test
    public void countUsingReduceFor() {
        // BEGIN count_using_reduce_for
        int acc = 0;
        for (Integer element : asList(1, 2, 3)) {
            acc = acc + element;
        }
        assertEquals(6, acc);
        // END count_using_reduce_for
    }

    @Test
    public void functionalStringsWithNumbers() {
        // BEGIN strings_numbers_filter
        List<String> beginningWithNumbers
                = Stream.of("a", "1abc", "abc1")
                .filter(value -> isDigit(value.charAt(0)))
                .collect(toList());

        assertEquals(Collections.singletonList("1abc"), beginningWithNumbers);
        // END strings_numbers_filter
    }

    @Test
    public void imperativeStringsWithNumbers() {
        // BEGIN strings_numbers_for
        List<String> beginningWithNumbers = new ArrayList<>();
        for(String value : asList("a", "1abc", "abc1")) {
            if (isDigit(value.charAt(0))) {
                beginningWithNumbers.add(value);
            }
        }

        assertEquals(Collections.singletonList("1abc"), beginningWithNumbers);
        // END strings_numbers_for
    }

    @Test
    public void flatMapCharacters() {
        // BEGIN flatmap_characters
        List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
                .flatMap(Collection::stream)
                .collect(toList());

        assertEquals(asList(1, 2, 3, 4), together);
        // END flatmap_characters
    }

    // Examples, IterationTest

    @Test
    public void lazyPrintOuts() {
        Iteration iteration = new Iteration();
        iteration.filterArtistsFromLondonPrinted(SampleData.membersOfTheBeatles);
    }

    @Test
    public void evaluatedPrintOuts() {
        Iteration iteration = new Iteration();
        iteration.internalCountArtistsFromLondonPrinted(SampleData.membersOfTheBeatles);
    }

    // Examples, RefactorTest

    @Test
    public void allStringJoins() {
        List<Supplier<Refactor.LongTrackFinder>> finders = Arrays.<Supplier<Refactor.LongTrackFinder>>asList(
                Refactor.Step0::new,
                Refactor.Step1::new,
                Refactor.Step2::new,
                Refactor.Step3::new,
                Refactor.Step4::new
        );

        List<Album> albums = unmodifiableList(asList(SampleData.aLoveSupreme, SampleData.sampleShortAlbum));
        List<Album> noTracks = unmodifiableList(Collections.singletonList(sampleShortAlbum));

        finders.forEach(finder -> {
            System.out.println("Testing: " + finder.toString());

            Refactor.LongTrackFinder longTrackFinder = finder.get();
            Set<String> longTracks = longTrackFinder.findLongTracks(albums);

            assertEquals("[Acknowledgement, Resolution]", longTracks.toString());

            longTracks = longTrackFinder.findLongTracks(noTracks);

            assertTrue(longTracks.isEmpty());
        });
    }

    // Examples, StreamExercisesTest

    @Test
    public void external() {
        assertEquals(4, countBandMembersExternal(Arrays.asList(SampleData.johnColtrane, SampleData.theBeatles)));
    }

    @Test
    public void mapExample() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        List<Integer> values = StreamExercises.map(stream, x -> x + 1);
        assertEquals(Arrays.asList(2, 3, 4), values);
    }

    @Test
    public void mapExampleParallel() {
        Stream<Integer> parallelStream = Stream.of(1, 2, 3).parallel();
        List<Integer> values = StreamExercises.map(parallelStream, x -> x + 1);
        assertEquals(Arrays.asList(2, 3, 4), values);
    }

    // Exercises, duplicated with answers ?
}

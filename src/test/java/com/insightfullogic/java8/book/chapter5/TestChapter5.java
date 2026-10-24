package com.insightfullogic.java8.book.chapter5;

import com.insightfullogic.java8.book.domain.Artist;
import com.insightfullogic.java8.book.domain.SampleData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestChapter5 {

    // Answers, FibonacciTest

    @Test
    public void fibonacciMatchesOpeningSequence() {
        List<Long> fibonacciSequence = Arrays.asList(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L);

        IntStream.range(0, fibonacciSequence.size())
                .forEach(x -> {
                    Fibonacci fibonacci = new Fibonacci();
                    long result = fibonacci.fibonacci(x);
                    long expectedResult = fibonacciSequence.get(x);
                    assertEquals(expectedResult, result);
                });
    }

    // Answers, GroupingByTest

    @Test
    public void stringsByLength() {
        com.insightfullogic.java8.book.chapter5.GroupingBy<String, Integer> stringIntegerGroupingBy = new GroupingBy<>(String::length);
        Map<Integer,List<String>> results = Stream.of("a", "b", "cc", "dd")
                .collect(stringIntegerGroupingBy);

        System.out.println(results);

        assertEquals(2, results.size());
        assertEquals(asList("a", "b"), results.get(1));
        assertEquals(asList("cc", "dd"), results.get(2));
    }

    // Answers, LongestNameTest

    @Test
    public void findsLongestNameByReduce() {
        Artist artist = LongestName.byReduce(SampleData.getThreeArtists());
        assertEquals(SampleData.johnColtrane, artist);
    }

    @Test
    public void findsLongestNameByCollecting() {
        Artist artist = LongestName.byCollecting(SampleData.getThreeArtists());
        assertEquals(SampleData.johnColtrane, artist);
    }

    // Answers, WordCountTest

    @Test
    public void passesBookExample() {
        Stream<String> names = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        Map<String, Long> counts = WordCount.countWords(names);

        assertEquals(3, counts.size());
        assertEquals(Long.valueOf(3), counts.get("John"));
        assertEquals(Long.valueOf(2), counts.get("Paul"));
        assertEquals(Long.valueOf(1), counts.get("George"));
    }


    // Examples, EncounterOrderTest

    @Test
    public void listToStream() {
        // BEGIN LIST_TO_STREAM
        List<Integer> numbers = asList(1, 2, 3, 4);

        List<Integer> sameOrder = new ArrayList<>(numbers);
        Assert.assertEquals(numbers, sameOrder);
        // END LIST_TO_STREAM
    }

    // NB: to actually get this to fail you need to reverse the order of the numbers.
    @Test
    public void hashSetToStream() {
        // BEGIN HASHSET_TO_STREAM
        Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));

        List<Integer> sameOrder = new ArrayList<>(numbers);

        // This may not pass with 4,3,2,1
        Assert.assertEquals(asList(1, 2, 3, 4), sameOrder);
        // END HASHSET_TO_STREAM
    }

    @Test
    public void hashSetToStreamSorted() {
        // BEGIN HASHSET_TO_STREAM_SORTED
        Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));

        List<Integer> sameOrder = numbers.stream()
                .sorted()
                .collect(toList());

        Assert.assertEquals(asList(1, 2, 3, 4), sameOrder);
        // END HASHSET_TO_STREAM_SORTED
    }

    @Test
    public void toStreamMapped() {
        // BEGIN TO_STREAM_MAPPED
        List<Integer> numbers = asList(1, 2, 3, 4);

        List<Integer> stillOrdered = numbers.stream()
                .map(x -> x + 1)
                .collect(toList());

        // Reliable encounter ordering
        Assert.assertEquals(asList(2, 3, 4, 5), stillOrdered);

        Set<Integer> unordered = new HashSet<>(numbers);

        List<Integer> stillUnordered = unordered.stream()
                .map(x -> x + 1)
                .collect(toList());

        // Can't assume encounter ordering
        assertThat(stillUnordered, hasItem(2));
        assertThat(stillUnordered, hasItem(3));
        assertThat(stillUnordered, hasItem(4));
        assertThat(stillUnordered, hasItem(5));
        // END TO_STREAM_MAPPED
    }

    // Examples, StringCollectorTest

    @Test
    public void testIdentityConstraint() {
        // From javadoc of Collector interface:
        // The identity constraint says that for any partially accumulated result,
        // combining it with an empty result container must produce an equivalent result.
        // That is, for a partially accumulated result a that is the result of any series of
        // accumulator and combiner invocations, a must be equivalent to combiner.apply(a, supplier.get()).

        StringCollector collector = new StringCollector(", ", "<!--", "-->");
        Supplier<StringCombiner> supplier = collector.supplier();
        BiConsumer<StringCombiner, String> accumulator = collector.accumulator();
        BinaryOperator<StringCombiner> combiner = collector.combiner();
        Function<StringCombiner, String> finisher = collector.finisher();

        StringCombiner stringCombiner1 = supplier.get();
        accumulator.accept(stringCombiner1, "one");
        accumulator.accept(stringCombiner1, "two");
        String result1 = finisher.apply(stringCombiner1);

        StringCombiner stringCombiner2 = supplier.get();
        accumulator.accept(stringCombiner2, "one");
        accumulator.accept(stringCombiner2, "two");
        stringCombiner2 = combiner.apply(stringCombiner2, supplier.get());
        String result2 = finisher.apply(stringCombiner2);

        assertEquals(result1, result2);
    }

    @Test
    public void testAssociativityConstraint() {
        // From javadoc of Collector interface:
        // The associativity constraint says that splitting the computation must produce an equivalent result.

        StringCollector collector = new StringCollector(", ", "<!--", "-->");
        Supplier<StringCombiner> supplier = collector.supplier();
        BiConsumer<StringCombiner, String> accumulator = collector.accumulator();
        BinaryOperator<StringCombiner> combiner = collector.combiner();
        Function<StringCombiner, String> finisher = collector.finisher();

        StringCombiner a1 = supplier.get();
        accumulator.accept(a1, "one");
        accumulator.accept(a1, "two");
        String r1 = finisher.apply(a1);  // result without splitting

        StringCombiner a2 = supplier.get();
        accumulator.accept(a2, "one");
        StringCombiner a3 = supplier.get();
        accumulator.accept(a3, "two");
        String r2 = finisher.apply(combiner.apply(a2, a3));  // result with splitting

        assertEquals(r1,  r2);
    }

    @Test
    public void testCollectEmpty() {
        Stream<String> stream = Stream.of();
        String result = stream.collect(new StringCollector(", ", "<!--", "-->"));
        assertEquals("<!---->", result);
    }

    @Test
    public void testCollectSimple() {
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        String result = stream.collect(new StringCollector(", ", "<!--", "-->"));
        assertEquals("<!--one, two, three, four-->", result);
    }

    @Test
    public void testCollectPrallel() {
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        String result = stream.parallel().collect(new StringCollector(", ", "<!--", "-->"));
        assertEquals("<!--one, two, three, four-->", result);
    }

    @Test
    public void testCollectPrallelLargeDataSet() {
        List<String> data = IntStream.iterate(1, i -> i + 1)
                .mapToObj(Integer::toString)
                .limit(10000)
                .collect(Collectors.toList());

        String simpleResult = data.stream().collect(new StringCollector(", ", "<!--", "-->"));
        String parallelResult = data.parallelStream().collect(new StringCollector(", ", "<!--", "-->"));
        assertEquals(simpleResult, parallelResult);
    }

    // Examples, StringCombinerTest

    private StringCombiner combiner;

    @Before
    public void before() {
        this.combiner = new StringCombiner(", ", "[", "]");

        combiner.add("A").add("B").add("C").add("D");
    }

    @Test
    public void add() throws Exception {
        assertEquals("[A, B, C, D]", combiner.toString());
    }

    @Test
    public void mergeWithOther() throws Exception {
        StringCombiner other = new StringCombiner(", ", "[", "]");

        other.add("E").add("F").add("G");

        this.combiner.merge(other);

        assertEquals("[A, B, C, D, E, F, G]", this.combiner.toString());
    }

    @Test
    public void mergeWithEmpty() {
        this.combiner.merge(new StringCombiner(", ", "[", "]"));

        assertEquals("[A, B, C, D]", this.combiner.toString());
    }

    @Test
    public void mergeSelf() throws Exception {
        assertEquals("[A, B, C, D]", this.combiner.merge(this.combiner).toString());
    }

    @Test
    public void twiceCallToString() throws Exception {
        assertEquals("[A, B, C, D]", this.combiner.toString());
        assertEquals("[A, B, C, D]", this.combiner.toString());
    }

    // Examples, StringExamplesTest

    @Test
    public void beatlesExample() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        joiner.add("John");
        joiner.add("Paul");
        joiner.add("Ringo");
        assertEquals("[John, Paul, Ringo]", joiner.toString());
    }

    @Test
    public void allStringJoins() {
        List<Function<List<Artist>, String>> formatters = Arrays.asList(
                StringExamples::formatArtists,
                StringExamples::formatArtistsForLoop,
                StringExamples::formatArtistsRefactor1,
                StringExamples::formatArtistsRefactor2,
                StringExamples::formatArtistsRefactor3,
                StringExamples::formatArtistsRefactor4,
                StringExamples::formatArtistsRefactor5
        );

        formatters.forEach(formatter -> {
            System.out.println("Testing: " + formatter.toString());
            String result = formatter.apply(SampleData.getThreeArtists());
            assertEquals("[John Coltrane, John Lennon, The Beatles]", result);

            result = formatter.apply(Collections.emptyList());
            assertEquals("[]", result);
        });
    }

    @Test
    public void explicitForLoop() {
        String result = StringExamples.formatArtists(SampleData.getThreeArtists());
        assertEquals("[John Coltrane, John Lennon, The Beatles]", result);
    }

    // Exercises, duplicated with answers ?

}

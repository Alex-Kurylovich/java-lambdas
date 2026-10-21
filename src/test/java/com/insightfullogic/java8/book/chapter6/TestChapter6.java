package com.insightfullogic.java8.book.chapter6;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestChapter6 {

    // Answers, BuggyReduceTest

    @Test
    public void sample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int result = BuggyReduce.multiplyThrough(numbers);
        assertEquals(30, result);
    }

    // Answers, SerialToParallel

    @Test
    public void testSerialToParallel() {
        IntStream range = IntStream.range(0, 100);
        assertEquals(328350, SerialToParallel.sumOfSquares(range));
    }

    // Examples, ArrayExamplesTest, see ArrayExamplesTest.java

    // Examples, MovingAverageTest

    @Test
    public void smallArray() {
        double[] input = {0, 1, 2, 3, 4, 3.5};
        double[] result = ArrayExamples.simpleMovingAverage(input, 3);
        System.out.println(Arrays.toString(result));
        double[] expected = {1, 2, 3, 3.5};
        assertArrayEquals(expected, result, 0.0);
    }

    // Exercises, duplicated with answers ?
}

package com.insightfullogic.java8.book.chapter6;

import java.util.stream.IntStream;

public class SerialToParallel {

    public static int sumOfSquares(IntStream range) {
        return range.parallel()
                    .map(x -> x * x)
                    .sum();
    }

}

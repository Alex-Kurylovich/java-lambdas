package com.insightfullogic.java8.book.chapter6;

import java.util.List;

public class BuggyReduce {

    public static int multiplyThrough(List<Integer> numbers) {
        return 5 * numbers.parallelStream()
                .reduce(1, (acc, x) -> x * acc);
    }

}

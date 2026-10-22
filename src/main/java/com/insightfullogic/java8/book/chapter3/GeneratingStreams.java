package com.insightfullogic.java8.book.chapter3;

import java.util.stream.Stream;

public class GeneratingStreams {

    public static void main(String[] args) {
        iterate();
    }

    static void iterate() {
        Stream.iterate(0, x -> x + 1)

                .limit(5)
                .forEach(System.out::println);
    }

}

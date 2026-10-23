package com.insightfullogic.java8.book.chapter6;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
public class OptimisationExampleFixed {

    public static void main(String[] ignore) throws RunnerException, IOException {
        final String[] args = {
                ".*OptimisationExampleFixed.*",
                "-wi",
                "10",
                "-i",
                "10",
                "-f",
                "1"
        };
        Main.main(args);
    }

}

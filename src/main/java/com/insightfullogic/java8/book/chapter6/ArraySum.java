package com.insightfullogic.java8.book.chapter6;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class ArraySum {

    public static void main(String[] ignore) throws IOException, RunnerException {
        final String[] args = {
                ".*ArraySum.*",
                "-wi",
                "5",
                "-i",
                "5"
        };
        Main.main(args);
    }

}

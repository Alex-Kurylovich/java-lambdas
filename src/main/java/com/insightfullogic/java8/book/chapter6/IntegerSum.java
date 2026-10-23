package com.insightfullogic.java8.book.chapter6;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class IntegerSum {

    public static void main(String[] ignore) throws IOException, RunnerException {
        final String[] args = {
                ".*IntegerSum.*",
                "-wi",
                "10",
                "-i",
                "20",
                "-f",
                "1"
        };
        Main.main(args);
    }

    private static final int size = Integer.getInteger("sum.size", 1000000);

    public int[] array;

    @GenerateMicroBenchmark
    public int range() {
        return IntStream.range(0, size).parallel().sum();
    }

    @GenerateMicroBenchmark
    public int serialRange() {
        return IntStream.range(0, size).sum();
    }

    @GenerateMicroBenchmark
    public int array() {
        return IntStream.of(array).parallel().sum();
    }

}

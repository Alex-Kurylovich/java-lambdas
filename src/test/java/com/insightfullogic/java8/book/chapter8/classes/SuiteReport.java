package com.insightfullogic.java8.book.chapter8.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class SuiteReport {

    private final String name;
    private final List<SpecificationReport> specifications;

    public SuiteReport(String name) {
        this.name = name;
        specifications = new ArrayList<>();
    }

    public void add(SpecificationReport specification) {
        specifications.add(specification);
    }

    public Stream<SpecificationReport> specifications() {
        return specifications.stream();
    }

    public String getName() {
        return name;
    }

}

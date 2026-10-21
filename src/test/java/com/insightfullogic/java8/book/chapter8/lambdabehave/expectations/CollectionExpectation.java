package com.insightfullogic.java8.book.chapter8.lambdabehave.expectations;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class CollectionExpectation extends BoundExpectation {

    private final Collection<?> objectUnderTest;

    public CollectionExpectation(Collection<?> objectUnderTest) {
        super(objectUnderTest);
        this.objectUnderTest = objectUnderTest;
    }

    public void isEmpty() {
        assertTrue(objectUnderTest.isEmpty());
    }

}

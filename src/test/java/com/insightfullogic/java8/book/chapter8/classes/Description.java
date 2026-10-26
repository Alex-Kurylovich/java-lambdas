package com.insightfullogic.java8.book.chapter8.classes;

import com.insightfullogic.java8.book.chapter8.interfaces.Specification;

public class Description {

    private final String suite;

    public Description(String suite) {
        this.suite = suite;
    }

    // BEGIN should
public void should(String description, Specification specification) {
    try {
        Expect expect = new Expect();
        specification.specifyBehaviour(expect);
        Runner.current.recordSuccess(suite, description);
    } catch (AssertionError cause) {
        Runner.current.recordFailure(suite, description, cause);
    } catch (Throwable cause) {
        Runner.current.recordError(suite, description, cause);
    }
}
    // END should

}

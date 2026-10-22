package com.insightfullogic.java8.book.chapter4;

// BEGIN body
public interface Child extends Parent {

    @Override
    default void welcome() {
        message("Child: Hi!");
    }

}
// END body

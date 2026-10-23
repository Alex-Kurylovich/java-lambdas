package com.insightfullogic.java8.book.chapter4;

// BEGIN body
public interface Parent {

    void message(String body);

    default void welcome() {
        message("Parent: Hi!");
    }

}
// END body

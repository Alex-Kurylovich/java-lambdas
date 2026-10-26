package com.insightfullogic.java8.book.chapter4.interfaces;

// BEGIN body
public interface Carriage {

    default String rock() {
        return "... from side to side";
    }

}
// END body

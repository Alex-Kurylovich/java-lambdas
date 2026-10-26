package com.insightfullogic.java8.book.chapter4.interfaces;

// BEGIN body
public interface Jukebox {

    default String rock() {
        return "... all over the world!";
    }

}
// END body

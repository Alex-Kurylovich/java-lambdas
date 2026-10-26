package com.insightfullogic.java8.book.chapter8.statics;

import com.insightfullogic.java8.book.chapter8.classes.Description;
import com.insightfullogic.java8.book.chapter8.interfaces.Suite;

public class Lets {

    // BEGIN describe
public static void describe(String name, Suite behavior) {
    Description description = new Description(name);
    behavior.specifySuite(description);
}
    // END describe

}

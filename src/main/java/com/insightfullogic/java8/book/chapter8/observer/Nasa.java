package com.insightfullogic.java8.book.chapter8.observer;

// BEGIN NASA
public class Nasa implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("We made it!");
        }
    }
}
// END NASA

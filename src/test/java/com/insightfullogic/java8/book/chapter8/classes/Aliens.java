package com.insightfullogic.java8.book.chapter8.classes;

import com.insightfullogic.java8.book.chapter8.interfaces.LandingObserver;

public class Aliens implements LandingObserver {

    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("They're distracted, lets invade earth!");
        }
    }

}

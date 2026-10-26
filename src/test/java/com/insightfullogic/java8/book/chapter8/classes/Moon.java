package com.insightfullogic.java8.book.chapter8.classes;

import com.insightfullogic.java8.book.chapter8.interfaces.LandingObserver;

import java.util.ArrayList;
import java.util.List;

public class Moon {

    private final List<LandingObserver> observers = new ArrayList<>();

    public void land(String name) {
        for (LandingObserver observer : observers) {
            observer.observeLanding(name);
        }
    }

    public void startSpying(LandingObserver observer) {
        observers.add(observer);
    }

    public static void main(String[] args) {
        classBasedExample();
        lambdaBasedExample();
    }

    private static void classBasedExample() {
        Moon moon = new Moon();
        moon.startSpying(new Nasa());
        moon.startSpying(new Aliens());

        moon.land("An asteroid");
        moon.land("Apollo 11");
    }

    private static void lambdaBasedExample() {
        Moon moon = new Moon();

        moon.startSpying(name -> {
            if (name.contains("Apollo"))
                System.out.println("We made it!");
        });

        moon.startSpying(name -> {
            if (name.contains("Apollo"))
                System.out.println("They're distracted, lets invade earth!");
        });

        moon.land("An asteroid");
        moon.land("Apollo 11");
    }

}

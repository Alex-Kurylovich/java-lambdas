package com.insightfullogic.java8.book.chapter8.classes;

import com.insightfullogic.java8.book.chapter8.interfaces.Action;

import java.util.ArrayList;
import java.util.List;

public class Macro {

    private final List<Action> actions;

    public Macro() {
        actions = new ArrayList<>();
    }

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Action::perform);
    }

}

package com.insightfullogic.java8.book.chapter4.classes;

import com.insightfullogic.java8.book.chapter4.interfaces.Carriage;
import com.insightfullogic.java8.book.chapter4.interfaces.Jukebox;

public class MusicalCarriage
        implements Carriage, Jukebox {

    @Override
    public String rock() {
        return Carriage.super.rock();
    }

}

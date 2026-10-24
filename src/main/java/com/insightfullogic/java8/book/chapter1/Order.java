package com.insightfullogic.java8.book.chapter1;


import java.util.List;

public abstract class Order {

    protected final List<Album> albums;

    public Order(List<Album> albums) {
        this.albums = albums;
    }

}

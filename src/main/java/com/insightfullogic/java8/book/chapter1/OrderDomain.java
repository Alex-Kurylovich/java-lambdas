package com.insightfullogic.java8.book.chapter1;


import java.util.List;
import java.util.function.ToLongFunction;

public class OrderDomain extends Order {

    public OrderDomain(List<Album> albums) {
        super(albums);
    }

    // BEGIN body
    public long countFeature(ToLongFunction<Album> function) {
        return albums.stream()
                .mapToLong(function)
                .sum();
    }

    // END body

}

package com.insightfullogic.java8.book.domain;


import java.util.List;
import java.util.function.ToLongFunction;

public class OrderDomain extends Order {

    public OrderDomain(List<Album> albums) {
        super(albums);
    }

    public long countFeature(ToLongFunction<Album> function) {
        return albums.stream()
                .mapToLong(function)
                .sum();
    }

}

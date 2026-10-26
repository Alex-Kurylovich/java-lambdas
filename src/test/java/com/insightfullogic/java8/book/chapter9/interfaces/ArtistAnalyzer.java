package com.insightfullogic.java8.book.chapter9.interfaces;

import java.util.function.Consumer;

public interface ArtistAnalyzer {

    void isLargerGroup(String artistName,
                       String otherArtistName,
                       Consumer<Boolean> handler);
    
}

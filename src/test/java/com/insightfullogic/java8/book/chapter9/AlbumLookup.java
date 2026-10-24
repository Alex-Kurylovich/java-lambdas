package com.insightfullogic.java8.book.chapter9;

import com.insightfullogic.java8.book.domain.Album;

public interface AlbumLookup {
    Album lookupByName(String albumName);
}

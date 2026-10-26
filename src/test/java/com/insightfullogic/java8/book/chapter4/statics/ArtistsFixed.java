package com.insightfullogic.java8.book.chapter4.statics;

import com.insightfullogic.java8.book.domain.Artist;

import java.util.List;
import java.util.Optional;

public class ArtistsFixed {


    public static Optional<Artist> getArtist(List<Artist> artists, int  index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }

    public static String getArtistName(List<Artist> artists, int index) {
        Optional<Artist> artist = getArtist(artists, index);
        return artist.map(Artist::getName)
                     .orElse("unknown");
    }

}

package com.insightfullogic.java8.book.chapter4;

import com.insightfullogic.java8.book.domain.Artist;

import java.util.List;
import java.util.Optional;

public class ArtistsFixed {

    private final List<Artist> artists;

    public ArtistsFixed(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }

    public String getArtistName(int index) {
        Optional<Artist> artist = getArtist(index);
        return artist.map(Artist::getName)
                     .orElse("unknown");
    }

}

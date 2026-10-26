package com.insightfullogic.java8.book.chapter9.classes;

import com.insightfullogic.java8.book.chapter9.interfaces.AlbumLookup;
import com.insightfullogic.java8.book.domain.Album;
import com.insightfullogic.java8.book.domain.Artist;
import com.insightfullogic.java8.book.domain.Track;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableAlbumLookup implements AlbumLookup {

    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(4);

    private final List<Track> tracks;
    private final List<Artist> artists;

    public CompletableAlbumLookup(List<Track> tracks, List<Artist> artists) {
        this.tracks = tracks;
        this.artists = artists;
    }

    // BEGIN lookupByName
    public Album lookupByName(String albumName) {
        CompletableFuture<List<Artist>> artistLookup
                = loginTo("artist")
                .thenCompose(artistLogin -> lookupArtists());  // <1>

        return loginTo("track")
                .thenCompose(trackLogin -> lookupTracks(albumName, trackLogin)) // <2>
                .thenCombine(artistLookup, (tracks, artists)
                        -> new Album(albumName, tracks, artists)) // <3>
                .join(); // <4>
    }

    private CompletableFuture<List<Artist>> lookupArtists() {
        return CompletableFuture.completedFuture(artists);
    }

    private void sleep() {
        try {
            Thread.sleep((long) 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private CompletableFuture<List<Track>> lookupTracks(String albumName, Credentials credentials) {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            return tracks;
        }, SERVICE);
    }

    private CompletableFuture<Credentials> loginTo(String serviceName) {
        return CompletableFuture.supplyAsync(() -> {
            if ("artist".equals(serviceName)) {
                sleep();
            }
            return new Credentials();
        }, SERVICE);
    }

}

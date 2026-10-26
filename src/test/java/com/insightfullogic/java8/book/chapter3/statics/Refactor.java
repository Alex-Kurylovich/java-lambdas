package com.insightfullogic.java8.book.chapter3.statics;

import com.insightfullogic.java8.book.domain.Album;
import com.insightfullogic.java8.book.domain.Track;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Refactor {

    public interface LongTrackFinder {
        Set<String> findLongTracks(List<Album> albums);
    }

    public static class Step0 implements LongTrackFinder {
        public Set<String> findLongTracks(List<Album> albums) {
            Set<String> trackNames = new HashSet<>();
            for (Album album : albums) {
                for (Track track : album.getTrackList()) {
                    if (track.getLength() > 60) {
                        String name = track.getName();
                        trackNames.add(name);
                    }
                }
            }
            return trackNames;
        }
    }

    public static class Step1 implements LongTrackFinder {
        public Set<String> findLongTracks(List<Album> albums) {
            Set<String> trackNames = new HashSet<>();
            albums
                    .forEach(album -> {
                        album.getTracks()
                                .forEach(track -> {
                                    if (track.getLength() > 60) {
                                        String name = track.getName();
                                        trackNames.add(name);
                                    }
                                });
                    });
            return trackNames;
        }
        // END findLongTracks_1
    }

    public static class Step2 implements LongTrackFinder {
         public Set<String> findLongTracks(List<Album> albums) {
            Set<String> trackNames = new HashSet<>();
            albums
                    .forEach(album -> {
                        album.getTracks()
                                .filter(track -> track.getLength() > 60)
                                .map(Track::getName)
                                .forEach(trackNames::add);
                    });
            return trackNames;
        }
    }

    public static class Step3 implements LongTrackFinder {
        public Set<String> findLongTracks(List<Album> albums) {
            Set<String> trackNames = new HashSet<>();

            albums.stream()
                    .flatMap(Album::getTracks)
                    .filter(track -> track.getLength() > 60)
                    .map(Track::getName)
                    .forEach(trackNames::add);

            return trackNames;
        }
     }

    public static class Step4 implements LongTrackFinder {
        public Set<String> findLongTracks(List<Album> albums) {
            return albums.stream()
                    .flatMap(Album::getTracks)
                    .filter(track -> track.getLength() > 60)
                    .map(Track::getName)
                    .collect(toSet());
        }
    }

}

package com.cartisan.java8.examples.chapter3;

import com.cartisan.java8.examples.chapter1.Album;
import com.cartisan.java8.examples.chapter1.Track;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Refactor {
    public static interface LongTrackFinder {
        public Set<String> findLongTracks(List<Album> albums);
    }

    public static class Step0 implements LongTrackFinder {
        @Override
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
        @Override
        public Set<String> findLongTracks(List<Album> albums) {
            Set<String> trackNames = new HashSet<>();
            albums.stream().forEach(album->{
                album.getTracks().forEach(track -> {
                    if (track.getLength() > 60) {
                        String name = track.getName();
                        trackNames.add(name);
                    }
                });
            });

            return trackNames;
        }
    }

    public static class Step2 implements LongTrackFinder {
        @Override
        public Set<String> findLongTracks(List<Album> albums) {
            Set<String> trackNames = new HashSet<>();
            albums.stream().forEach(album -> {
                album.getTracks()
                        .filter(track -> track.getLength()>60)
                        .map(track -> track.getName())
                        .forEach(name->trackNames.add(name));
            });

            return trackNames;
        }
    }

    public static class Step3 implements LongTrackFinder {
        @Override
        public Set<String> findLongTracks(List<Album> albums) {
            Set<String> trackNames = new HashSet<>();

            albums.stream()
                    .flatMap(album->album.getTracks())
                    .filter(track -> track.getLength()>60)
                    .map(track -> track.getName())
                    .forEach(name->trackNames.add(name));

            return trackNames;
        }
    }

    public static class Step4 implements LongTrackFinder{

        @Override
        public Set<String> findLongTracks(List<Album> albums) {
            return albums.stream()
                    .flatMap(album->album.getTracks())
                    .filter(track -> track.getLength()>60)
                    .map(track -> track.getName())
                    .collect(toSet());
        }
    }




}

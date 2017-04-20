package com.cartisan.java8.answers.chapter4;

import com.cartisan.java8.examples.chapter1.Artist;

import java.util.stream.Stream;

public interface PerformanceFixed {
    String getName();

    Stream<Artist> getMusicians();

    default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> Stream.concat(Stream.of(artist), artist.getMembers()));
    }
}

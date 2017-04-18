package com.cartisan.java8.examples.chapter1;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;

public interface Performance {
    String getName();

    Stream<Artist> getMusicians();

    public default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> concat(of(artist), artist.getMembers()));
    }
}

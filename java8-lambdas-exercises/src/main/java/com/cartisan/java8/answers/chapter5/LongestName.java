package com.cartisan.java8.answers.chapter5;

import com.cartisan.java8.examples.chapter1.Artist;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.maxBy;

public class LongestName {
    private static Comparator<Artist> byNameLength = comparing(artist -> artist.getName().length());

    public static Artist byReduce(List<Artist> artists) {
        return artists.stream().reduce((acc, artist) -> {
            return (byNameLength.compare(acc, artist) >= 0) ? acc : artist;
        }).orElseThrow(RuntimeException::new);
    }

    public static Artist byCollecting(List<Artist> artists) {
        return artists.stream().collect(maxBy(byNameLength)).orElseThrow(RuntimeException::new);
    }
}

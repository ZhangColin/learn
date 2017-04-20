package com.cartisan.java8.answers.chapter4;

import com.cartisan.java8.examples.chapter1.Artist;

import java.util.stream.Stream;

public interface Performance {
    String getName();

    Stream<Artist> getMusicians();
}

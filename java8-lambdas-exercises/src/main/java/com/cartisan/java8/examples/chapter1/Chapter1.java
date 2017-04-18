package com.cartisan.java8.examples.chapter1;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Chapter1 extends MusicChapter {
    public List<String> getNamesOfArtists_lambda() {
        return artists.stream()
                .map(artist -> artist.getName())
                .collect(toList());
    }

    public List<String> getNameOfArtists_MethodReference() {
        return artists.stream()
                .map(Artist::getName)
                .collect(toList());
    }

    public List<Artist> artistsLivingInLondon() {
        return artists.stream()
                .filter(artist -> "London".equals(artist.getNationality()))
                .collect(toList());
    }
}

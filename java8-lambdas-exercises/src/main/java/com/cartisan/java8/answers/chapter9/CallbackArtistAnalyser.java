package com.cartisan.java8.answers.chapter9;

import com.cartisan.java8.examples.chapter1.Artist;

import java.util.function.Consumer;
import java.util.function.Function;

public class CallbackArtistAnalyser implements ArtistAnalyzer {
    private final Function<String, Artist> artistLookupService;

    public CallbackArtistAnalyser(Function<String, Artist> artistLookupService) {
        this.artistLookupService = artistLookupService;
    }

    @Override
    public void isLargeGroup(String artistName, String otherArtistName, Consumer<Boolean> handler) {
        boolean isLarger = getNumberOfMembers(artistName) > getNumberOfMembers(otherArtistName);
        handler.accept(isLarger);
    }

    private long getNumberOfMembers(String artistName) {
        return artistLookupService.apply(artistName).getMembers().count();
    }
}

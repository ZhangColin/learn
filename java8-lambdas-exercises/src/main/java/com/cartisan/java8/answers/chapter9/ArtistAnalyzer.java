package com.cartisan.java8.answers.chapter9;

import java.util.function.Consumer;

public interface ArtistAnalyzer {
    void isLargeGroup(String artistName, String otherArtistName, Consumer<Boolean> handler);
}

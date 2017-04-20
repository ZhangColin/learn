package com.cartisan.java8.examples.chapter4;

public interface Jukebox {
    default String rock() {
        return "... all over the world!";
    }
}

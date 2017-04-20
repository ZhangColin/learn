package com.cartisan.java8.examples.chapter4;

public interface Carriage {
    default String rock() {
        return "... from side to side";
    }
}

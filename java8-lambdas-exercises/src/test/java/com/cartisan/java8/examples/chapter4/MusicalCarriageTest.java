package com.cartisan.java8.examples.chapter4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MusicalCarriageTest {
    @Test
    public void rocksLikeACarriage() {
        assertEquals("... from side to side", new MusicalCarriage().rock());
    }
}

package com.cartisan.java8.answers.chapter9;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlockingArtistAnalyzerTest {
    private final BlockingArtistAnalyzer analyzer =
            new BlockingArtistAnalyzer(new FakeLookupService()::lookupArtistName);

    @Test
    public void largerGroupsAreLarger() {
        assertTrue(analyzer.isLargerGroup("The Beatles", "John Coltrane"));
    }

    @Test
    public void smallerGroupsArentLarger() {
        assertFalse(analyzer.isLargerGroup("John Coltrane", "The Beatles"));
    }
}

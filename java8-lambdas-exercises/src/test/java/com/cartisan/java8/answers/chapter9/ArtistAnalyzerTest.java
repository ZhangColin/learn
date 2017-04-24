package com.cartisan.java8.answers.chapter9;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ArtistAnalyzerTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        FakeLookupService lookupService = new FakeLookupService();
        Object[][] data = new Object[][]{
                {new CallbackArtistAnalyser(lookupService::lookupArtistName)},
                {new CompletableFutureArtistAnalyser(lookupService::lookupArtistName)}
        };

        return Arrays.asList(data);
    }

    private final ArtistAnalyzer analyzer;

    public ArtistAnalyzerTest(ArtistAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    @Test
    public void largeGroupsAreLarger() {
        assertLargeGroup(true, "The Beatles", "John Coltrane");
    }

    @Test
    public void smallerGroupsArentLarger() {
        assertLargeGroup(false, "John Coltrane", "The Beatles");
    }

    private void assertLargeGroup(boolean expected, String artistName, String otherArtistName) {
        AtomicBoolean isLarger = new AtomicBoolean(!expected);
        analyzer.isLargeGroup(artistName, otherArtistName, isLarger::set);
        assertEquals(expected, isLarger.get());
    }

}

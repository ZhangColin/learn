package com.cartisan.java8.examples.chapter9;

import com.cartisan.java8.examples.chapter1.Artist;
import com.cartisan.java8.examples.chapter1.SampleData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RxExamplesTest {
    @Test
    public void filtersAlbums() {
        RxExamples examples = new RxExamples(SampleData.getThreeArtists());
        Artist artist = examples.search("John", "UK", 5).toBlocking().single();

        assertEquals(SampleData.johnLennon, artist);
    }
}
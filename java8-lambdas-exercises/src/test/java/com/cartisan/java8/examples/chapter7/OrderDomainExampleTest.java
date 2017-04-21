package com.cartisan.java8.examples.chapter7;

import com.cartisan.java8.examples.chapter1.Album;
import com.cartisan.java8.examples.chapter4.OrderDomain;
import org.junit.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class OrderDomainExampleTest {
    @Test
    public void canCountFeatures() {
        OrderDomain order = new OrderDomain(asList(
                newAlbum("Exile on Main St."),
                newAlbum("Beggars Banquet"),
                newAlbum("Aftermath"),
                newAlbum("Let it Bleed")));

        assertEquals(8, order.countFeature(album -> 2));
    }

    private Album newAlbum(String name) {
        return new Album(name, emptyList(), emptyList());
    }
}

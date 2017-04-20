package com.cartisan.java8.examples.chapter4;

import com.cartisan.java8.examples.chapter1.Album;

import java.util.List;
import java.util.function.ToLongFunction;

public class OrderDomain extends Order {
    public OrderDomain(List<Album> albums) {
        super(albums);
    }

    public long countFeature(ToLongFunction<Album> function) {
        return albums.stream().mapToLong(function).sum();
    }

    @Override
    public long countRunningTime() {
        return countFeature(album->album.getTracks().mapToLong(track->track.getLength()).sum());
    }

    @Override
    public long countMusicians() {
        return countFeature(album->album.getMusicians().count());
    }

    @Override
    public long countTracks() {
        return countFeature(album->album.getTracks().count());
    }
}

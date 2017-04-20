package com.cartisan.java8.examples.chapter4;

import com.cartisan.java8.examples.chapter1.Album;

import java.util.List;

public class OrderStreams extends Order {
    public OrderStreams(List<Album> albums) {
        super(albums);
    }

    @Override
    public long countRunningTime() {
        return albums.stream().mapToLong(album->album.getTracks().mapToLong(track->track.getLength()).sum()).sum();
    }

    @Override
    public long countMusicians() {
        return albums.stream().mapToLong(album->album.getMusicians().count()).sum();
    }

    @Override
    public long countTracks() {
        return albums.stream().mapToLong(album->album.getTracks().count()).sum();
    }
}

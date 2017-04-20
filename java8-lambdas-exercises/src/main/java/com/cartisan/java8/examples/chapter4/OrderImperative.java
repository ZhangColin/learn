package com.cartisan.java8.examples.chapter4;

import com.cartisan.java8.examples.chapter1.Album;
import com.cartisan.java8.examples.chapter1.Track;

import java.util.List;

public class OrderImperative extends Order {
    public OrderImperative(List<Album> albums) {
        super(albums);
    }

    @Override
    public long countRunningTime() {
        long count = 0;
        for (Album album : albums) {
            for (Track track : album.getTrackList()) {
                count += track.getLength();
            }
        }

        return count;
    }

    @Override
    public long countMusicians() {
        long count = 0;
        for (Album album : albums) {
            count += album.getMusicianList().size();
        }
        return count;
    }

    @Override
    public long countTracks() {
        long count = 0;
        for (Album album : albums) {
            count += album.getTrackList().size();
        }
        return count;
    }
}

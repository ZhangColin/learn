package com.cartisan.java8.examples.chapter4;

import com.cartisan.java8.examples.chapter1.Album;

import java.util.IntSummaryStatistics;

public class Primitives {
    public static void printTrackLengthStatistics(Album album) {
        IntSummaryStatistics trackLengthStats = album.getTracks()
                .mapToInt(track->track.getLength())
                .summaryStatistics();

        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                trackLengthStats.getMax(),
                trackLengthStats.getMin(),
                trackLengthStats.getAverage(),
                trackLengthStats.getSum());
    }
}

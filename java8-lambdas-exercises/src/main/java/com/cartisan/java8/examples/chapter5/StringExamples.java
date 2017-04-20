package com.cartisan.java8.examples.chapter5;

import com.cartisan.java8.examples.chapter1.Artist;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;

public class StringExamples {
    public static String formatArtists(List<Artist> artists) {
        String result = artists.stream().map(Artist::getName)
                .collect(joining(", ", "[", "]"));
        return result;
    }

    public static String formatArtistsForLoop(List<Artist> artists) {
        StringBuilder builder = new StringBuilder("[");
        for (Artist artist : artists) {
            if (builder.length() > 1) {
                builder.append(", ");
            }
            String name = artist.getName();
            builder.append(name);
        }
        builder.append("]");

        String result = builder.toString();
        return result;
    }

    public static String formatArtistsRefactor1(List<Artist> artists) {
        StringBuilder builder = new StringBuilder("[");
        artists.stream().map(Artist::getName)
                .forEach(name ->{
                    if (builder.length() > 1) {
                        builder.append(", ");
                    }
                    builder.append(name);
                });
        builder.append("]");
        return builder.toString();
    }

    public static String formatArtistsRefactor2(List<Artist> artists) {
        StringBuilder reduced = artists.stream().map(Artist::getName)
                .reduce(new StringBuilder(), (builder, name) ->{
                    if (builder.length() > 0) {
                        builder.append(", ");
                    }
                    builder.append(name);

                    return builder;
                }, (left, right)->left.append(right));
        reduced.insert(0, "[");
        reduced.append("]");

        return reduced.toString();
    }

    public static String formatArtistsRefactor3(List<Artist> artists) {
        StringCombiner combined = artists.stream().map(Artist::getName)
                .reduce(new StringCombiner(", ", "[", "]"),
                        StringCombiner::add, StringCombiner::merge);
        return combined.toString();
    }

    public static String formatArtistsRefactor4(List<Artist> artists) {
        return artists.stream().map(Artist::getName)
                .reduce(new StringCombiner(", ", "[", "]"),
                        StringCombiner::add, StringCombiner::merge).toString();
    }

    public static String formatArtistsRefactor5(List<Artist> artists) {
        return artists.stream().map(Artist::getName)
                .collect(new StringCollector(", ", "[", "]"));
    }

    public static String formatArtistsReducing(List<Artist> artists) {
        return artists.stream().map(Artist::getName)
                .collect(reducing(
                        new StringCombiner(", ", "[", "]"),
                        name->new StringCombiner(", ", "[", "]").add(name),
                        StringCombiner::merge
                )).toString();
    }
}

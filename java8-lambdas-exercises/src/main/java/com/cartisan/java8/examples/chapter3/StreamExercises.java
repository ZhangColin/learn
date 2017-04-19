package com.cartisan.java8.examples.chapter3;

import com.cartisan.java8.examples.chapter1.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamExercises {
    public static int countBandMembersExternal(List<Artist> artists) {
        int totalMembers = 0;
        for (Artist artist : artists) {
            Stream<Artist> members = artist.getMembers();
            totalMembers+=members.count();
        }

        return totalMembers;
    }

    public static <T, R> List<R> map(Stream<T> stream, Function<T, R> mapper) {
        return stream.reduce(new ArrayList<>(), (acc, value)->{
            ArrayList<R> result = new ArrayList<>();
            result.addAll(acc);
            System.out.println("accumulator");
            result.add(mapper.apply(value));
            return result;
        }, (left, right)->{
            System.out.println("combinder");
            ArrayList<R> result = new ArrayList<>();
            result.addAll(left);
            result.addAll(right);
            return result;
        });

    }
}

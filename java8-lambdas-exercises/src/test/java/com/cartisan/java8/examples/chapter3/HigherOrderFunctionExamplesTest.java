package com.cartisan.java8.examples.chapter3;

import com.cartisan.java8.examples.chapter1.Track;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HigherOrderFunctionExamplesTest {
    @Test
    public void collectToList() {
        List<String> collected = Stream.of("a", "b", "c").collect(toList());

        assertEquals(asList("a", "b", "c"), collected);
    }

    @Test
    public void mapToUpperCase() {
        List<String> collected = Stream.of("a", "b", "hello")
                .map(string->string.toUpperCase())
                .collect(toList());

        assertEquals(asList("A", "B", "HELLO"), collected);
    }

    @Test
    public void forToUpperCase() {
        List<String> collected = new ArrayList<>();
        for (String string : asList("a", "b", "hello")) {
            String uppercaseString = string.toUpperCase();
            collected.add(uppercaseString);
        }

        assertEquals(asList("A", "B", "HELLO"), collected);
    }

    @Test
    public void imperativeMaxLength() {
        List<Track> tracks = asList(
                new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451)
        );

        Track shortestTrack = tracks.get(0);
        for (Track track : tracks) {
            if (track.getLength() < shortestTrack.getLength()) {
                shortestTrack = track;
            }
        }

        assertEquals(tracks.get(1), shortestTrack);
    }

    @Test
    public void streamsMaxLength() {
        List<Track> tracks = asList(
                new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451)
        );

        Track shortestTrack = tracks.stream().min(comparing(track -> track.getLength())).get();

        assertEquals(tracks.get(1), shortestTrack);
    }

    @Test
    public void streamsAny() {
        List<Track> tracks = asList(
                new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451)
        );

        boolean matchLength = tracks.stream().anyMatch(track -> track.getLength() > 500);

        assertTrue(matchLength);
    }

    @Test
    public void imperativeAnyMatch() {
        List<Track> tracks = asList(
                new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451)
        );

        boolean matchLength = false;

        for (Track track : tracks) {
            if (track.getLength() > 500) {
                matchLength=true;
            }
        }

        assertTrue(matchLength);
    }

    @Test
    public void sumUsingReduce() {
        int count = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
        assertEquals(6, count);
    }

    @Test
    public void expandedReduce() {
        BinaryOperator<Integer> accumulator = (acc, element)->acc+element;
        int count = accumulator.apply(accumulator.apply(accumulator.apply(0, 1), 2), 3);

        assertEquals(6, count);
    }

    @Test
    public void countUsingReduceFor() {
        int acc = 0;
        for (Integer element : asList(1,2,3)) {
            acc+=element;
        }

        assertEquals(6, acc);
    }

    @Test
    public void functionalStringsWithNumbers() {
        List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1")
                .filter(value->isDigit(value.charAt(0)))
                .collect(toList());

        assertEquals(asList("1abc"), beginningWithNumbers);
    }

    @Test
    public void imperativeStringsWithNumbers() {
        List<String> beginningWithNumbers = new ArrayList<>();
        for (String value : asList("a", "1abc", "abc1")) {
            if (isDigit(value.charAt(0))) {
                beginningWithNumbers.add(value);
            }
        }

        assertEquals(asList("1abc"), beginningWithNumbers);
    }

    @Test
    public void flatMapCharacters() {
        List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
                .flatMap(numbers->numbers.stream())
                .collect(toList());

        assertEquals(asList(1,2,3,4), together);
    }
}

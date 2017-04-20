package com.cartisan.java8.examples.chapter5;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class EncounterOrderTest {
    @Test
    public void listToStream() {
        List<Integer> numbers = asList(1, 2, 3, 4);
        List<Integer> sameOrder = numbers.stream().collect(toList());

        assertEquals(numbers, sameOrder);
    }

    @Ignore
    @Test
    public void hashSetToStream() {
        Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));
        List<Integer> sameOrder = numbers.stream().collect(toList());

        assertEquals(asList(4, 3, 2, 1), sameOrder);
    }

    @Test
    public void hashSetToStreamSorted() {
        Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));
        List<Integer> sameOrder = numbers.stream().sorted().collect(toList());

        assertEquals(asList(1, 2, 3, 4), sameOrder);
    }

    @Test
    public void toStreamMapped() {
        List<Integer> numbers = asList(1, 2, 3, 4);

        List<Integer> stillOrdered = numbers.stream().map(x->x+1).collect(toList());

        assertEquals(asList(2,3,4,5), stillOrdered);

        Set<Integer> unordered = new HashSet<>(numbers);

        List<Integer> stillUnordered = unordered.stream().map(x->x+1).collect(toList());

        assertThat(stillUnordered, hasItem(2));
        assertThat(stillUnordered, hasItem(3));
        assertThat(stillUnordered, hasItem(4));
        assertThat(stillUnordered, hasItem(5));
    }
}

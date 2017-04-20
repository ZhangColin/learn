package com.cartisan.java8.examples.chapter5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCombinerTest {
    private StringCombiner combiner;

    @Before
    public void before() {
        this.combiner = new StringCombiner(", ", "[", "]");
        combiner.add("A").add("B").add("C").add("D");
    }

    @Test
    public void add() {
        assertEquals("[A, B, C, D]", combiner.toString());
    }

    @Test
    public void mergeWithOther() {
        StringCombiner other = new StringCombiner(", ", "[", "]");
        other.add("E").add("F").add("G");
        this.combiner.merge(other);
        assertEquals("[A, B, C, D, E, F, G]", combiner.toString());
    }

    @Test
    public void mergeWithEmpty() {
        this.combiner.merge(new StringCombiner(", ", "[", "]"));

        assertEquals("[A, B, C, D]", combiner.toString());
    }

    @Test
    public void mergeSelf() {
        assertEquals("[A, B, C, D]", combiner.merge(this.combiner).toString());
    }

    @Test
    public void twiceCallToString() {
        assertEquals("[A, B, C, D]", combiner.toString());
        assertEquals("[A, B, C, D]", combiner.toString());
    }

}

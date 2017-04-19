package com.cartisan.java8.answers.chapter3;

import com.cartisan.java8.examples.chapter1.SampleData;
import org.junit.Test;

import static com.cartisan.java8.answers.chapter3.Question2.countBandMembersInternal;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class Question2Test {
    @Test
    public void internal() {
        assertEquals(4, countBandMembersInternal(asList(SampleData.johnColtrane, SampleData.theBeatles)));
    }
}
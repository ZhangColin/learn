package com.cartisan.java8.answers.chapter3;

import org.junit.Test;

import java.util.Collections;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StringExercisesTest {
    @Test
    public void noLowercaseLettersInAnEmptyString() {
        assertEquals(0, StringExercises.countLowercaseLetters(""));
    }

    @Test
    public void countsLowercaseLetterExample() {
        assertEquals(3, StringExercises.countLowercaseLetters("aBcDeF"));
    }

    @Test
    public void supportsNoLowercaseLetters() {
        assertEquals(0, StringExercises.countLowercaseLetters("ABCDEF"));
    }

    @Test
    public void noStringReturnedForEmptyList() {
        assertFalse(StringExercises.mostLowercaseString(Collections.emptyList()).isPresent());
    }

    @Test
    public void findsMostLowercaseString() {
        Optional<String> result = StringExercises.mostLowercaseString(asList("a", "abc", "ABCde"));
        assertEquals(Optional.of("abc"), result);
    }

}
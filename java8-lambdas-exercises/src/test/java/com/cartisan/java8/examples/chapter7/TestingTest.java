package com.cartisan.java8.examples.chapter7;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class TestingTest {
    @Test
    public void multipleWordsToUppercase() {
        List<String> input = asList("a", "b", "hello");
        List<String> result = Testing.allToUpperCase(input);

        assertEquals(asList("A", "B", "HELLO"), result);
    }

    @Test
    public void twoLetterStringConvertedToUppercaseLambdas() {
        List<String> input = asList("ab");
        List<String> result = Testing.elementFirstToUpperCaseLambdas(input);

        assertEquals(asList("Ab"), result);
    }

    @Test
    public void twoLetterStringConvertedToUppercase() {
        String input = "ab";
        String result = Testing.firstToUppercase(input);

        assertEquals("Ab", result);
    }

}

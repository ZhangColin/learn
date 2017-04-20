package com.cartisan.java8.examples.chapter4;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OptionalExampleTest {
    @Test
    public void examples() {
        Optional<String> a = Optional.of("a");
        assertEquals("a", a.get());

        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);
        assertFalse(emptyOptional.isPresent());

        assertTrue(a.isPresent());

        assertEquals("b", emptyOptional.orElse("b"));
        assertEquals("c", emptyOptional.orElseGet(()->"c"));
    }
}

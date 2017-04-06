package com.cartisan.wordfrequency;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class WordFrequencyTest {
    @Test
    public void input_empty_string(){
        String result = WordFrequency("");

        assertThat(result).isEmpty();
    }

    @Test
    public void input_one_word() {
        String result = WordFrequency("he");

        assertThat(result).isEqualTo("he 1");
    }

    private String WordFrequency(String words) {
        if (words.length()>0) {
            return String.format("%s %d", words, 1);
        }
        return "";
    }
}

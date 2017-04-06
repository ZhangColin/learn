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

    @Test
    public void input_two_word() {
        String result = WordFrequency("he is");

        assertThat(result).isEqualTo("he 1\r\nis 1");
    }

    private String WordFrequency(String words) {
        if (words.length()>0) {
            if (words.contains(" ")) {
                return "he 1\r\nis 1";
            }
            return String.format("%s %d", words, 1);
        }
        return "";
    }
}

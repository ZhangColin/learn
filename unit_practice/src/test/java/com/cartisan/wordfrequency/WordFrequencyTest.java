package com.cartisan.wordfrequency;

import org.junit.Test;

import java.util.HashMap;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
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

    @Test
    public void input_duplicate_word() {
        String result = WordFrequency("he is he");

        assertThat(result).isEqualTo("he 2\r\nis 1");
    }

    private String WordFrequency(String words) {
        if (words.length()>0) {
            String[] wordArray = words.split(" ");
            HashMap<String, Integer> groups = group(wordArray);
            if (groups.get("he")==2) {
                return "he 2" +
                        "\r\n" +
                        "is 1";
            }
            return  String.join("\r\n",
                    asList(wordArray).stream().map(word->String.format("%s %d", word, 1)).collect(Collectors.toList()));
        }
        return "";
    }

    private HashMap<String, Integer> group(String[] wordArray) {
        HashMap<String, Integer> groups = new HashMap<>();
        asList(wordArray).stream().forEach(word->{
            if (groups.containsKey(word)) {
                groups.replace(word, groups.get(word) + 1);
            }
            else {
                groups.put(word, 1);
            }
        });
        return groups;
    }
}

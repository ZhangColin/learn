package com.cartisan.wordfrequency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


public class WordFrequencyTest {
    @Test
    public void input_empty_string() {
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
        if (words.length() > 0) {
            String[] wordArray = words.split(" ");
            List<Group> groups = group(wordArray);
            return String.join("\r\n",
                    groups.stream().map(group -> String.format("%s %d", group.getWord(), group.getCount())).collect(Collectors.toList()));
        }
        return "";
    }

    private List<Group> group(String[] wordArray) {
        List<Group> groups = new ArrayList<>();
        List<String> words = new ArrayList<>();
        asList(wordArray).stream().forEach(word -> {
            if (words.contains(word)) {
                Group group = groups.get(words.indexOf(word));
                group.setCount(group.getCount() + 1);
            } else {
                Group group = new Group();
                group.setWord(word);
                group.setCount(1);
                groups.add(group);
                words.add(word);
            }

        });
        return groups;
    }

    public static class Group {
        private String word;
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }
}

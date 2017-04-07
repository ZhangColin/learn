package com.cartisan.wordfrequency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
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

    @Test
    public void sort_input_word() {
        String result = WordFrequency("he is is");

        assertThat(result).isEqualTo("is 2\r\nhe 1");
    }

    @Test
    public void word_include_multiple_space() {
        String result = WordFrequency("he   is");

        assertThat(result).isEqualTo("he 1\r\nis 1");
    }

    private String WordFrequency(String content) {
        if (content.length() > 0) {
            String[] wordArray = split(content);
            List<Group> groups = group(wordArray);
            Collections.sort(groups );
            return format(groups);
        }
        return "";
    }

    private String format(List<Group> groups) {
        return String.join("\r\n",
                groups.stream().map(group -> group.toString()).collect(Collectors.toList()));
    }

    private String[] split(String content) {
        return content.split("\\s+");
    }

    private List<Group> group(String[] wordArray) {
        List<Group> groups = new ArrayList<>();
        List<String> words = new ArrayList<>();
        asList(wordArray).stream().forEach(word -> {
            if (words.contains(word)) {
                Group group = groups.get(words.indexOf(word));
                group.increment();
            } else {
                Group group = new Group(word);
                groups.add(group);
                words.add(word);
            }

        });
        return groups;
    }

    public static class Group implements Comparable<Group> {
        public Group(String word) {
            this.word = word;
            this.count=1;
        }

        private String word;
        private int count;

        public int getCount() {
            return count;
        }

        public void increment() {
            count++;
        }

        public String getWord() {
            return word;
        }

        @Override
        public String toString() {
            return String.format("%s %d", getWord(), getCount());
        }

        @Override
        public int compareTo(Group o) {
            return o.getCount()-getCount();
        }
    }
}

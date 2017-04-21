package com.cartisan.java8.examples.chapter6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WordCounting {
    public static void main(String[] args) {
        InputStream huckleberryFinn = WordCounting.class.getResourceAsStream("huckleberry_finn");
        new WordCounting().countWords(huckleberryFinn);
    }

    private static final Pattern space = Pattern.compile("\\s+");

    private void countWords(InputStream stream) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))){
            Map<String, Long> counts = reader.lines()
                    .flatMap(space::splitAsStream)
                    .map(String::trim)
                    .filter(word->!word.isEmpty())
                    .collect(groupingBy(word->word, counting()));

            counts.forEach((word, count)-> System.out.println(word+" -> "+count));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

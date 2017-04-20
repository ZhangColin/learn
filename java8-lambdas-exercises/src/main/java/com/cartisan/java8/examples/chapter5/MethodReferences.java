package com.cartisan.java8.examples.chapter5;

import com.cartisan.java8.examples.chapter1.Album;
import com.cartisan.java8.examples.chapter1.Artist;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.cartisan.java8.examples.chapter5.CollectorExamples.countWords;
import static java.util.stream.Collectors.groupingBy;

public class MethodReferences {
    public Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician));
    }

    private static final Pattern SPACES = Pattern.compile("\\w+");

    public static Map<String, Long> countWordsIn(Path path) throws IOException {
        Stream<String> words = Files.readAllLines(path, Charset.defaultCharset())
                .stream().flatMap(SPACES::splitAsStream);

        return countWords(words);
    }
}

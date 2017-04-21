package com.cartisan.java8.examples.chapter7;

import com.cartisan.java8.examples.chapter1.Album;
import com.cartisan.java8.examples.chapter1.Artist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Testing {
    private static final Logger logger = Logger.getLogger("Testing");

    public static List<String> allToUpperCase(List<String> words) {
        return words.stream()
                .map(string -> string.toUpperCase())
                .collect(toList());
    }

    public static List<String> elementFirstToUpperCaseLambdas(List<String> words) {
        return words.stream().map(value -> {
            char firstChar = Character.toUpperCase(value.charAt(0));
            return firstChar + value.substring(1);
        }).collect(toList());
    }

    public static List<String> elementFirstToUppercase(List<String> words) {
        return words.stream().map(Testing::firstToUppercase)
                .collect(toList());
    }

    public static String firstToUppercase(String value) {
        char firstChar = Character.toUpperCase(value.charAt(0));
        return firstChar + value.substring(1);
    }

    public static Set<String> imperativeNationalityReport(Album album) {
        Set<String> nationalities = new HashSet<>();
        for (Artist artist : album.getMusicianList()) {
            if (artist.getName().startsWith("The")) {
                String nationality = artist.getNationality();
                System.out.println("Found nationality: "+nationality);
                nationalities.add(nationality);
            }
        }

        return nationalities;
    }

    public static Set<String> forEachLoggingFailure(Album album) {
        album.getMusicians().filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .forEach(nationality -> System.out.println("Found: "+nationality));

        Set<String> nationalities = album.getMusicians().filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .collect(Collectors.toSet());

        return nationalities;
    }

    public static Set<String> nationalityReportUsingPeek(Album album) {
        Set<String> nationalities = album.getMusicians().filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .peek(nation -> System.out.println("Found nationality: "+nation))
                .collect(Collectors.toSet());

        return nationalities;
    }
}

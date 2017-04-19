package com.cartisan.java8.answers.chapter3;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

public class StringExercises {
    public static int countLowercaseLetters(String string) {
        return (int)string.chars().filter(Character::isLowerCase).count();
    }

    public static Optional<String> mostLowercaseString(List<String> strings) {
        return strings.stream().max(comparing(StringExercises::countLowercaseLetters));
    }
}

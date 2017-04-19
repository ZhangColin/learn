package com.cartisan.java8.answers.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterUsingReduce {
    public static <T> List<T> filter(Stream<T> stream, Predicate<T> predicate) {
        List<T> initial = new ArrayList<>();

        return stream.reduce(initial, (List<T> acc, T x) -> {
            if (predicate.test(x)) {
                List<T> newAcc = new ArrayList<>(acc);
                newAcc.add(x);
                return newAcc;
            }
            else {
                return acc;
            }
        }, FilterUsingReduce::combineLists);
    }

    private static <T> List<T> combineLists(List<T> left, List<T> right) {
        List<T> newLeft = new ArrayList<>(left);
        newLeft.addAll(right);
        return newLeft;
    }
}

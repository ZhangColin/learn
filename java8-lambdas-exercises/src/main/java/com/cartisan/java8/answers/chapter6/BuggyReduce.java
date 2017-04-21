package com.cartisan.java8.answers.chapter6;

import java.util.List;

public class BuggyReduce {
    public static int multiplythrough(List<Integer> numbers) {
        return 5 * numbers.parallelStream().reduce(1, (acc, x) -> x * acc);
    }
}

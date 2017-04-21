package com.cartisan.java8.examples.chapter6;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayExamples {
    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);

        int start = n -1;
        return IntStream.range(start, sums.length)
                .mapToDouble(i->{
                    double prefix = i == start ? 0 : sums[i - n];
                    return (sums[i] - prefix) / n;
                }).toArray();
    }

    public static double[] parallelInitialize(int size) {
        double[] values = new double[size];
        Arrays.parallelSetAll(values, i -> i);

        return values;
    }

    public static double[] imperativeInitilize(int size) {
        double[] values = new double[size];
        for (int i = 0; i < values.length; i++) {
            values[i]=i;
        }

        return values;
    }
}

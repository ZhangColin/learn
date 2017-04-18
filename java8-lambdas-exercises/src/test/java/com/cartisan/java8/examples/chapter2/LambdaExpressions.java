package com.cartisan.java8.examples.chapter2;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class LambdaExpressions {
    public void first_lambda_1() {
        JButton button = new JButton();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button clicked");
            }
        });
    }

    public void first_lambda_2() {
        JButton button = new JButton();

        button.addActionListener(event -> System.out.println("button clicked"));
    }

    public void first_lambda_3() {
        JButton button = new JButton();
        button.addActionListener((ActionEvent event) -> System.out.println("button clicked"));
    }

    public void allLambdaForms() {
        Runnable noArguments = () -> System.out.println("Hello World");
        ActionListener oneArgument = event -> System.out.println("button clicked");

        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println("World");
        };

        BinaryOperator<Long> add = (x, y) -> x + y;

        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
    }

    public void first_lambda_5() {
        JButton button = new JButton();

        final String name = getUserName();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi "+name);
            }
        });
    }

    public void first_lambda_6() {
        JButton button = new JButton();

        final String name = getUserName();
        button.addActionListener(event -> System.out.println("hi "+name));
    }

    public void diamondInference() {
        Map<String, Integer> oldWordCounts = new HashMap<String, Integer>();
        Map<String, Integer> diamonWordCounts = new HashMap<>();
    }

    public void diamondInferenceMethod() {
        useHashmap(new HashMap<>());
    }

    private void useHashmap(HashMap<String, String> values) {

    }

    public void firstBiFunction() {
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
    }

    public void biFunctionDeclaration() {
        BiFunction<Integer, Integer, Integer> add;
    }

    public void typeInferenceExamples() {
        Predicate<Integer> atLeast5 = x -> x > 5;
    }

    public void typeInferenceExample2() {
        BinaryOperator<Long> addLongs = (x, y) -> x + y;
    }

    @Test
    public void mostSpecific() {
        overloadedMethod("abc");
    }

    private void overloadedMethod(Object o) {
        System.out.println("Object");
    }

    private void overloadedMethod(String s) {
        System.out.println("String");
    }

    @Test
    public void mostSpecificBiFunction() {
        overloadedMethod((x, y)->x+y);
    }

    private interface IntegerBiFunction extends BinaryOperator<Integer> {
    }

    private void overloadedMethod(BinaryOperator<Integer> lambda) {
        System.out.println("BinaryOperator");
    }

    private void overloadedMethod(IntegerBiFunction lambda) {
        System.out.println("IntegerBinaryOperator");
    }

//    @Test
//    public void mostSpecificPredicate() {
//        overloadedMethod((x)->true);
//    }
//
//    private interface IntPredicate {
//        boolean test(int value);
//    }
//
//    private void overloadedMethod(Predicate<Integer> predicate) {
//        System.out.println("Predicate");
//    }
//
//    private void overloadedMethod(IntPredicate predicate) {
//        System.out.println("IntPredicate");
//    }


    private String getUserName() {
        return "Colin";
    }
}

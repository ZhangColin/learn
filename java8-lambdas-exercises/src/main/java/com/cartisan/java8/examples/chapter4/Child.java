package com.cartisan.java8.examples.chapter4;

public interface Child extends Parent {
    @Override
    default void welcome() {
        message("Child: Hi!");
    }
}

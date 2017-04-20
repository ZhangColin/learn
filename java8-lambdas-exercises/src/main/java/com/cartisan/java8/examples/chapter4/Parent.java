package com.cartisan.java8.examples.chapter4;

public interface Parent {
    void message(String body);

    default void welcome() {
        message("Parent: Hi!");
    }

    String getLastMessage();
}

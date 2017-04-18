package com.cartisan.java8.examples.chapter2;

import javax.swing.*;

public class CaptureCompileError {
    private JButton button;

    public void error() {
        String name = getUserName();
        name = formatUserName(name);
    }

    private String formatUserName(String name) {
        return name.toLowerCase();
    }

    private String getUserName() {
        return "COLIN";
    }
}

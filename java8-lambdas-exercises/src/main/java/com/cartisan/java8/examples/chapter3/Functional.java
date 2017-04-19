package com.cartisan.java8.examples.chapter3;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Functional {
    private JButton button = new JButton();

    private ActionEvent lastEvent;

    private void registerHandler() {
        button.addActionListener(event-> this.lastEvent = event);
    }
}

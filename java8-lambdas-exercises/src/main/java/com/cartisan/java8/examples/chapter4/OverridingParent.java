package com.cartisan.java8.examples.chapter4;

public class OverridingParent extends ParentImpl{
    @Override
    public void welcome() {
        message("Class Parent: Hi!");
    }
}

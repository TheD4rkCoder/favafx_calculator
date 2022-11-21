package com.bx.fallmerayer.javafxtest.favafx_test;

public class Node {
    private String component;
    private Node left;
    private Node right;

    public void setComponent (String component){
        this.component = component;
    }

    public void setLeft (Node node){
        this.left = node;
    }

    public void setRight (Node node){
        this.right = node;
    }

    public String getComponent() {
        return component;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}

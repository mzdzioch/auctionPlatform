package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class Node<T>{

    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private T item = null;

    public Node() {
    }

    public Node(T item) {
        this.item = item;
    }

    public Node(Node<T> parent, T item) {
        this.parent = parent;
        this.item = item;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void addChild(T data){
        Node<T> newChild = new Node<T>(data);
        newChild.setParent(this);
        this.children.add(newChild);

    }

    public void addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isRoot(){
        return (this.parent == null);
    }

    public boolean isLeaf(){
        if(this.children.size() == 0)
            return true;
        else
            return false;
    }

    public void addChildren(List<Node> children) {
        for(Node t : children) {
            t.setParent(this);
        }
        //this.children.addAll(children);
    }




}

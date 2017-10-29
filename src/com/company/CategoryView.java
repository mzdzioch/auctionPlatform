package com.company;

import java.util.List;

public class CategoryView {

    private Node<Category> node = (new CategoryBuilder()).getBuilder();
    private List<Node<Category>> nodeList = node.getChildren();
    //private String str = "-";

    public void display(){
        displayCategory(nodeList);
    }

    private void displayCategory(List<Node<Category>> lists) {

        if(!lists.isEmpty()) {
            for (Node<Category> categoryNode : lists) {
                System.out.println(categoryNode.getItem().toString());

                displayCategory(categoryNode.getChildren());
            }
        }
    }
}

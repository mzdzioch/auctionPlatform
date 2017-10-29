package com.company;

public class NodeTest{
    public static void main(String[] args) {

        CategoryView ct = new CategoryView();
        CategoryBuilder categoryBuilder = new CategoryBuilder();

        Node<Category> root = categoryBuilder.getBuilder();

        //ct.display(root.getChildren());

        //categoryBuilder.addRootCategory(4, "Erotic");

        root.addChild(new Category(4, "Erotic"));


        ct.display(root.getChildren());

        System.out.println("---------------");

        categoryBuilder.addRootCategory(5, "Ferniture");

        Node<Category> newRoot = categoryBuilder.getBuilder();

        ct.display(newRoot.getChildren());

    }



}
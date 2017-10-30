package com.company;

public class NodeTest{
    public static void main(String[] args) {

        CategoryView ct = new CategoryView();
        CategoryBuilder categoryBuilder = new CategoryBuilder();

        Node<Category> root = categoryBuilder.getBuilder();

        //ct.display(root.getChildren());

        //categoryBuilder.addRootCategory(4, "Erotic");

        root.addChild(new Category(4, "Erotic"));
        categoryBuilder.addSubCategory("Motors","Bus");
        categoryBuilder.addSubCategory("Electronics", "Video");
        categoryBuilder.addSubCategory("Electronics", "USB");



        ct.display(root.getChildren());

        //ct.display();


    }



}
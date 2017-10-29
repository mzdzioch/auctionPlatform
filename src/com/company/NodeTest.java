package com.company;

public class NodeTest{
    public static void main(String[] args) {
        CategoryView ct = new CategoryView();

        ct.display();


        CategoryBuilder categoryBuilder = new CategoryBuilder();

        categoryBuilder.addRootCategory(4, "Erotic");
        System.out.println("---------------");
        ct.display();
    }



}
package com.company;

import java.util.*;

public class CategoryBuilder {

    private List<Node<String>> categories = new ArrayList<>();



    public List<Node<String>> createCategory() {


        Node<String> computers = new Node<>("Computers");
        Node<String> cars = new Node<>("Cars");

        Node<String> child1 = new Node<>("Servers");
        child1.addChild("CPU");
        child1.addChild("RAM");

        Node<String> child2 = new Node<>("Desktops");
        child2.addChild("Worstations");

        Node<String> child3 = new Node<>("Laptops");
        child2.addChild("Notbook");

        computers.addChild(child1);
        computers.addChild(child2);
        computers.addChild(child3);


        categories.add(computers);
        categories.add(cars);

        return categories;
    }

    public void displayCategory() {

        List<Node<String>> categories = createCategory();

        for (Node<String> category : categories) {
            System.out.println("-" + category.getItem());

            for (Node node : category.getChildren()) {
                System.out.println("--" + node.getItem());
                List<Node> nList = node.getChildren();
                for (Node node1 : nList) {
                    System.out.println("---" + node1.getItem());
                }
            }
        }

    }
}

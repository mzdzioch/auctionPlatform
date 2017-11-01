package com.company.service;

import com.company.model.Category;
import com.company.model.Node;
import com.company.service.CategoryBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryView {

    private Node<Category> node;
    private List<Node<Category>> nodeList;
    private Map<Integer, Node<Category>> categoryMap = new HashMap<>();
    private int index = 0;


    public void display(List<Node<Category>> lists) {
        displayCategory(lists);
    }


    public void display() {
        index = 0;
        nodeList = getBuilder().getChildren();
        displayCategory(nodeList);
    }

    private void displayCategory(List<Node<Category>> lists) {

        if(lists.isEmpty())
            index--;

        if (!lists.isEmpty()) {

            for (Node<Category> categoryNode : lists) {
                index++;
                printTab(index);
                System.out.println(categoryNode.getItem().toString());
                displayCategory(categoryNode.getChildren());
            }

            index--;
        }
    }

    private void printTab(int number) {
        String tab = "";
        char x = '└';
        for(int i = 1; i < number; i++){
            tab += "|--";
        }
        tab += x + "--";
        System.out.print(tab);
    }

    private Node<Category> getBuilder(){
        return (new CategoryBuilder()).getBuilder();
    }
}

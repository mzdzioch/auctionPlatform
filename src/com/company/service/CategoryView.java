package com.company.service;

import com.company.model.Category;
import com.company.model.Node;
import com.company.service.CategoryBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryView {

    public void display() {
        int index = 0;
        List<Node<Category>> nodeList = getBuilder().getChildren();
        displayCategory(nodeList, index);
    }

    private void displayCategory(List<Node<Category>> lists, int index) {

        if(lists.isEmpty())
            index--;

        if (!lists.isEmpty()) {

            for (Node<Category> categoryNode : lists) {
                index++;
                printTab(index);
                System.out.println(categoryNode.getItem().toString());
                displayCategory(categoryNode.getChildren(), index);
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

package com.company.view;

import com.company.model.Category;
import com.company.model.Node;
import com.company.helpers.CategoryBuilder;

import java.util.List;

public class CategoryView {

    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int increaseIndex(int index){
        return index++;
    }

    public int decreaseIndex(){
        return index--;
    }

    public void display() {
        int index = 0;
        List<Node<Category>> nodeList = getBuilder().getChildren();
        displayCategory(nodeList, index);
    }

    private void displayCategory(List<Node<Category>> lists, int index) {

        if(lists.isEmpty()) {
            index--;
        }

        if (!lists.isEmpty()) {
            index++;

            for (Node<Category> categoryNode : lists) {

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

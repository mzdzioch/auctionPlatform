package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryView {

    private Node<Category> node = (new CategoryBuilder()).getBuilder();
    private List<Node<Category>> nodeList = node.getChildren();
    private Map<Integer, Node<Category>> categoryMap = new HashMap<>();


    public void display(List<Node<Category>> lists) {
        displayCategory(lists);
    }

    int index = 0;

    public void display() {
        index = 0;
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

    public void addSubCategory(int parentId, int id, String name) {
        Node<Category> parent = findParentById(parentId);
        parent.addChild(new Node<Category>(new Category(id, name)));
    }

    private Node<Category> findParentById(Integer parentId) {

        categoryMap = copyCategoryToMap();

        return categoryMap.get(parentId);
    }

    private Map<Integer, Node<Category>> copyCategoryToMap() {

        List<Node<Category>> nodeList = node.getChildren();
        writeToMap(nodeList);

        return categoryMap;
    }

    private void writeToMap(List<Node<Category>> lists) {

        if (!lists.isEmpty()) {
            for (Node<Category> categoryNode : lists) {
                categoryMap.put(categoryNode.getItem().getId(), categoryNode);
                writeToMap(categoryNode.getChildren());
            }
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
}

package com.company.service;

import com.company.model.Category;
import com.company.model.Node;

import java.util.*;

public class CategoryBuilder {

    private Node<Category> rootCategory;
    private List<Integer> idList;
    private Map<Integer, Node<Category>> categoryMap = new HashMap<>();

    public CategoryBuilder() {
        this.rootCategory = createCategory();
    }

    private Node<Category> createCategory() {

        rootCategory = new Node<Category>(null, new Category());
        rootCategory.addChild(new Category(1, "Electronics"));
        rootCategory.addChild(new Category(2, "Motors"));
        rootCategory.addChild(new Category(3, "Clothes"));

        List<Node<Category>> rootList = rootCategory.getChildren();
        //adding subcategories for Electronic category
        rootList.get(0).addChild(new Category(11, "Laptops"));
        rootList.get(0).addChild(new Category(12, "PC"));
        rootList.get(0).addChild(new Category(13, "Clothes"));
        //adding subcategories for Motors category
        rootList.get(1).addChild(new Category(21, "Parts"));
        rootList.get(1).addChild(new Category(22, "Cars"));
        rootList.get(1).addChild(new Category(23, "Vehicles"));
        rootList.get(1).addChild(new Category(24, "Trucks"));
        //adding subcategories for Clothes category
        rootList.get(2).addChild(new Category(31, "Women Clothing"));
        rootList.get(2).addChild(new Category(32, "Men Clothing"));
        rootList.get(2).addChild(new Category(33, "Shoes"));

        return rootCategory;
    }

    public Node<Category> getBuilder() {
        return rootCategory;
    }

    public void addRootCategory(int id, String name) {

        if (rootCategory.isRoot())
            rootCategory.addChild(new Category(id, name));
        else {
            rootCategory = new Node<Category>(null, new Category());
            rootCategory.addChild(new Category(id, name));
        }
    }

//    public void addSubCategory(int parentId, int id, String name) {
//        Node<Category> parent = findParentById(parentId);
//        parent.addChild(new Node<Category>(new Category(id, name)));
//    }

    public boolean addSubCategory(String parentCategory, String name) {
        categoryMap = copyCategoryToMap();

        if (findParentByName(parentCategory)) {
            for (Node<Category> categoryNode : categoryMap.values()) {
                if (categoryNode.getItem().getName().equals(parentCategory)) {
                    categoryNode.addChild(new Node<Category>(new Category(nextCategoryId(categoryNode), name)));
                    return true;
                }
            }
        }

        return false;
    }

    public List<Integer> getCategoryAndSubcategoriesListId(int categoryId){

        if(isParentExist(categoryId)){
            Node<Category> nodeTemp = findParentById(categoryId);

            idList = new ArrayList<>();
            idList.add(nodeTemp.getItem().getId());

            for (Node<Category> categoryNode : nodeTemp.getChildren()) {
                idList.add(categoryNode.getItem().getId());
            }
        }

        return idList;
    }

    private Node<Category> findParentById(Integer parentId) {
        categoryMap = copyCategoryToMap();

        return categoryMap.get(parentId);
    }

    private boolean isParentExist(Integer parentId) {
        categoryMap = copyCategoryToMap();

        if(categoryMap.get(parentId) == null)
            return false;
        else
            return true;
    }

    private boolean findParentByName(String parentName) {

        categoryMap = copyCategoryToMap();

        for (Node<Category> categoryNode : categoryMap.values()) {
            if (categoryNode.getItem().getName().equals(parentName))
                return true;
        }

        return false;
    }

    private Map<Integer, Node<Category>> copyCategoryToMap() {

        List<Node<Category>> nodeList = rootCategory.getChildren();

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

    private int nextCategoryId(Node<Category> parentNode) {
        return parentNode.getChildren().get(returnLastChildOfParent(parentNode)).getItem().getId() + 1;
    }

    private int returnLastChildOfParent(Node<Category> parentNode) {
        return parentNode.getChildren().size()-1;
    }


}

package com.company;

import java.util.*;

public class CategoryBuilder {

    private Node<Category> rootCategory;
    private Map<Integer, Node<Category>> categoryMap;

    public CategoryBuilder() {
        this.rootCategory = createCategory();
    }

    private Node<Category> createCategory() {

        rootCategory = new Node<Category>(null, new Category());
        Node<Category> electronicCategory = new Node<Category>(new Category(1, "Electronics"));
        Node<Category> motorsCategory = new Node<Category>(new Category(2, "Motors"));
        Node<Category> clothesCategory = new Node<Category>(new Category(3, "Clothes"));

        rootCategory.addChild(electronicCategory);
        rootCategory.addChild(motorsCategory);
        rootCategory.addChild(clothesCategory);

        Node<Category> laptopsCategory = new Node<Category>(new Category(12, "Laptops"));
        Node<Category> pcCategory = new Node<Category>(new Category(13, "PC"));
        Node<Category> serversCategory = new Node<Category>(new Category(14, "Servers"));

        electronicCategory.addChild(laptopsCategory);
        electronicCategory.addChild(pcCategory);
        electronicCategory.addChild(serversCategory);

        Node<Category> partsCategory = new Node<Category>(new Category(21, "Parts"));
        Node<Category> carsCategory = new Node<Category>(new Category(22, "Cars"));
        Node<Category> vehiclesCategory = new Node<Category>(new Category(23, "Vehicles"));
        Node<Category> trucksCategory = new Node<Category>(new Category(24, "Trucks"));

        motorsCategory.addChild(partsCategory);
        motorsCategory.addChild(carsCategory);
        motorsCategory.addChild(vehiclesCategory);
        motorsCategory.addChild(trucksCategory);

        Node<Category> womenClothingCategory = new Node<Category>(new Category(31, "Women Clothing"));
        Node<Category> menClothingCategory = new Node<Category>(new Category(32, "Men Clothing"));
        Node<Category> shoesCategory = new Node<Category>(new Category(33, "Shoes"));

        clothesCategory.addChild(womenClothingCategory);
        clothesCategory.addChild(menClothingCategory);
        clothesCategory.addChild(shoesCategory);

        return rootCategory;
    }

    public Node<Category> getBuilder(){
        return rootCategory;
    }

    public void addRootCategory(int id, String name) {
        if(rootCategory.isRoot())
            rootCategory.addChild(new Category(id, name));
        else {
            System.out.println("Jestesmy ");
            rootCategory = new Node<Category>(null, new Category());
            rootCategory.addChild(new Category(id, name));
        }

    }

    public void addSubCategory(int parentId, int id, String name) {
        Node<Category> parent = findParentById(parentId);
        parent.addChild(new Node<Category>(new Category(id, name)));
    }

    private Node<Category> findParentById(Integer parentId){
        categoryMap = mapOfCategory();

        return categoryMap.get(parentId);
    }

    private Map<Integer, Node<Category>> mapOfCategory() {

        List<Node<Category>> nodeList = rootCategory.getChildren();

        if(!nodeList.isEmpty()) {
            for (Node<Category> categoryNode : nodeList) {
                categoryMap.put(categoryNode.getItem().getId(), categoryNode);
            }
        }

        return categoryMap;
    }



}

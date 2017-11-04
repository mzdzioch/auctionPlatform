package com.company.model;

public class Category {
    private int idCategory;
    private String name;

    public Category() {
    }

    public Category(int idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "(id: " + idCategory +")";
    }
}

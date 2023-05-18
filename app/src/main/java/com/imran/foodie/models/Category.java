package com.imran.foodie.models;

public class Category {
    String categoryImage;
    String categoryName;

    public Category(String categoryImage, String categoryName) {
        this.categoryImage = categoryImage;
        this.categoryName = categoryName;
    }

    public Category() {
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

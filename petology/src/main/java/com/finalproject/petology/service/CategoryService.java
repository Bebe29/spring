package com.finalproject.petology.service;

import com.finalproject.petology.entity.Category;

public interface CategoryService {
    public Iterable<Category> getCategory();

    public Category addNewCategory(Category category);

    public Category updateCategory(Category category);

    // public void deleteCategory(int categoryId);
}
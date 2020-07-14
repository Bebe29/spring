package com.finalproject.petology.service;

import java.util.List;
import java.util.Optional;

import com.finalproject.petology.entity.Category;
import com.finalproject.petology.entity.Product;

public interface CategoryService {
    public Iterable<Category> getCategory();

    public Category addNewCategory(Category category);

    public Category updateCategory(Category category);

    // public void deleteCategory(int categoryId);

    public List<Product> getProductsOfCategory(int categoryId);

    public Optional<Category> getCategoryById(int categoryId);
}
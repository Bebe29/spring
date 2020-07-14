package com.finalproject.petology.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.finalproject.petology.dao.CategoryRepo;
// import com.finalproject.petology.dao.ProductRepo;
import com.finalproject.petology.entity.Category;
import com.finalproject.petology.entity.Product;
import com.finalproject.petology.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    // @Autowired
    // private ProductRepo productRepo;

    @Override
    @Transactional
    public Iterable<Category> getCategory() {
        return categoryRepo.findAll();
    };

    @Override
    @Transactional
    public Category addNewCategory(Category category) {
        category.setId(0);
        return categoryRepo.save(category);
    };

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        Category findCategory = categoryRepo.findById(category.getId()).get();
        if (findCategory == null)
            throw new RuntimeException("Category not found");
        return categoryRepo.save(category);
    };

    // @Override
    // @Transactional
    // public void deleteCategory(int categoryId) {
    // Category findCategory = categoryRepo.findById(categoryId).get();
    // if (findCategory == null)
    // throw new RuntimeException("Category not found");

    // findCategory.getProducts().forEach(product -> {
    // product.setCategory(null);
    // productRepo.save(product);
    // });

    // findCategory.setProducts(null);
    // categoryRepo.deleteById(categoryId);
    // };

    @Override
    @Transactional
    public List<Product> getProductsOfCategory(int categoryId) {
        Category findCategory = categoryRepo.findById(categoryId).get();
        return findCategory.getProducts();
    }

    @Override
    @Transactional
    public Optional<Category> getCategoryById(int categoryId) {
        return categoryRepo.findById(categoryId);
    }

}

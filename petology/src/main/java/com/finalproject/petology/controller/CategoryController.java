package com.finalproject.petology.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.finalproject.petology.dao.CategoryRepo;
import com.finalproject.petology.entity.Category;
import com.finalproject.petology.entity.Product;
import com.finalproject.petology.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping
    public Iterable<Category> getCategory() {
        return categoryService.getCategory();
    }

    @PostMapping
    public Category addNewCategory(@RequestBody Category category) {
        return categoryService.addNewCategory(category);
    }

    @PutMapping
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    // @DeleteMapping("/{categoryId}")
    // public void deleteCategory(@PathVariable int categoryId) {
    // categoryService.deleteCategory(categoryId);
    // }

    @GetMapping("/{categoryId}/products")
    public List<Product> getProductsOfCategory(@PathVariable int categoryId) {
        return categoryService.getProductsOfCategory(categoryId);
    }

    @GetMapping("/{categoryId}")
    public Optional<Category> getCategoryById(@PathVariable int categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

}
package com.finalproject.petology.dao;

import com.finalproject.petology.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
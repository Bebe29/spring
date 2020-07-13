package com.cimb.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.backend.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}

package com.finalproject.petology.dao;

import java.util.List;

import com.finalproject.petology.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products WHERE product_name LIKE %:productName%", nativeQuery = true)
    public Iterable<Product> findProductByName(@Param("productName") String searchProduct);

    @Query(value = "SELECT * FROM products ORDER BY product_name ASC", nativeQuery = true)
    public List<Product> sortProductByNameAsc();

    @Query(value = "SELECT * FROM products ORDER BY product_name DESC", nativeQuery = true)
    public List<Product> sortProductByNameDesc();

    @Query(value = "SELECT * FROM products ORDER BY price ASC", nativeQuery = true)
    public List<Product> sortProductByPriceAsc();

    @Query(value = "SELECT * FROM products ORDER BY price DESC", nativeQuery = true)
    public List<Product> sortProductByPriceDesc();

    @Query(value = "SELECT * FROM products WHERE category_id = :categoryId ORDER BY product_name ASC", nativeQuery = true)
    public List<Product> sortProductOfCategoryByNameAsc(@Param("categoryId") int categoryId);

    @Query(value = "SELECT * FROM products  WHERE category_id = :categoryId ORDER BY product_name DESC", nativeQuery = true)
    public List<Product> sortProductOfCategoryByNameDesc(@Param("categoryId") int categoryId);

    @Query(value = "SELECT * FROM products  WHERE category_id = :categoryId ORDER BY price ASC", nativeQuery = true)
    public List<Product> sortProductOfCategoryByPriceAsc(@Param("categoryId") int categoryId);

    @Query(value = "SELECT * FROM products WHERE category_id = :categoryId ORDER BY price DESC", nativeQuery = true)
    public List<Product> sortProductOfCategoryByPriceDesc(@Param("categoryId") int categoryId);

    @Query(value = "SELECT * FROM products LIMIT :pageSize OFFSET :offset", nativeQuery = true)
    public Iterable<Product> getPaginationDataProduct(@Param("pageSize") int pageSize, @Param("offset") int offset);
}
package com.finalproject.petology.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.finalproject.petology.entity.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    public Iterable<Product> getAllProduct();

    public Product addNewProducts(Product product, int categoryId);

    public String uploadImageProduct(MultipartFile file, String producString)
            throws JsonMappingException, JsonProcessingException;

    public ResponseEntity<Object> downloadImageProduct(String fileName);

    public Product editProduct(Product product);

    public void deleteProduct(int productId);

    public Optional<Product> getProductById(int productId);

    public Iterable<Product> getPaginationDataProduct(int pageSize, int page);

    // public Iterable<Product> filterProduct(String searchProduct);

    // public List<Product> sortProductByNameAsc();

    // public List<Product> sortProductByNameDesc();

    // public List<Product> sortProductByPriceAsc();

    // public List<Product> sortProductByPriceDesc();

    // public List<Product> sortProductOfCategoryByNameAsc(int categoryId);

    // public List<Product> sortProductOfCategoryByNameDesc(int categoryId);

    // public List<Product> sortProductOfCategoryByPriceAsc(int categoryId);

    // public List<Product> sortProductOfCategoryByPriceDesc(int categoryId);
}
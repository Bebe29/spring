package com.finalproject.petology.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.finalproject.petology.dao.ProductRepo;
import com.finalproject.petology.entity.Product;
import com.finalproject.petology.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Iterable<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @PostMapping("/categories/{categoryId}")
    public Product addNewProducts(@RequestBody Product product, @PathVariable int categoryId) {
        return productService.addNewProducts(product, categoryId);
    }

    @PostMapping
    public String uploadImageProduct(@RequestParam("file") MultipartFile file,
            @RequestParam("productData") String productString) throws JsonMappingException, JsonProcessingException {
        return productService.uploadImageProduct(file, productString);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Object> downloadImageProduct(@PathVariable String fileName) {
        return productService.downloadImageProduct(fileName);
    }

    @PutMapping
    public Product editProduct(@RequestBody Product product) {
        return productService.editProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}")
    public Optional<Product> getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping
    public Iterable<Product> getPaginationDataProduct(@RequestParam int pageSize, @RequestParam int page) {
        return productService.getPaginationDataProduct(pageSize, page);
    }

    // @GetMapping("/filter")
    // public Iterable<Product> filterProduct(@RequestParam String searchProduct) {
    // return productService.filterProduct(searchProduct);
    // }

    // @GetMapping("/sort/nameAsc")
    // public List<Product> sortProductByNameAsc() {
    // return productService.sortProductByNameAsc();
    // }

    // @GetMapping("/sort/nameDesc")
    // public List<Product> sortProductByNameDesc() {
    // return productService.sortProductByNameDesc();
    // }

    // @GetMapping("/sort/priceAsc")
    // public List<Product> sortProductByPriceAsc() {
    // return productService.sortProductByPriceAsc();
    // }

    // @GetMapping("/sort/priceDesc")
    // public List<Product> sortProductByPriceDesc() {
    // return productService.sortProductByPriceDesc();
    // }

    // @GetMapping("/sort/{categoryId}/nameAsc")
    // public List<Product> sortProductOfCategoryByNameAsc(@PathVariable int
    // categoryId) {
    // return productService.sortProductOfCategoryByNameAsc(categoryId);
    // }

    // @GetMapping("/sort/{categoryId}/nameDesc")
    // public List<Product> sortProductOfCategoryByNameDesc(@PathVariable int
    // categoryId) {
    // return productService.sortProductOfCategoryByNameDesc(categoryId);
    // }

    // @GetMapping("/sort/{categoryId}/priceAsc")
    // public List<Product> sortProductOfCategoryByPriceAsc(@PathVariable int
    // categoryId) {
    // return productService.sortProductOfCategoryByPriceAsc(categoryId);
    // }

    // @GetMapping("/sort/{categoryId}/priceDesc")
    // public List<Product> sortProductOfCategoryByPriceDesc(@PathVariable int
    // categoryId) {
    // return productService.sortProductOfCategoryByPriceDesc(categoryId);
    // }
}
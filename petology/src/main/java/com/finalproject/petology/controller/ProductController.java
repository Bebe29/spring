package com.finalproject.petology.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalproject.petology.dao.CategoryRepo;
import com.finalproject.petology.dao.ProductRepo;
import com.finalproject.petology.entity.Category;
import com.finalproject.petology.entity.Product;
import com.finalproject.petology.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping
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

    @GetMapping("/filter")
    public Iterable<Product> filterProduct(@RequestParam String searchProduct) {
        return productService.filterProduct(searchProduct);
    }

    @GetMapping("/sort/nameAsc")
    public List<Product> sortProductByNameAsc() {
        return productService.sortProductByNameAsc();
    }

    @GetMapping("/sort/nameDesc")
    public List<Product> sortProductByNameDesc() {
        return productRepo.sortProductByNameDesc();
    }

    @GetMapping("/sort/priceAsc")
    public List<Product> sortProductByPriceAsc() {
        return productRepo.sortProductByPriceAsc();
    }

    @GetMapping("/sort/priceDesc")
    public List<Product> sortProductByPriceDesc() {
        return productRepo.sortProductByPriceDesc();
    }

    @GetMapping("/sort/{categoryId}/nameAsc")
    public List<Product> sortProductOfCategoryByNameAsc(@PathVariable int categoryId) {
        return productService.sortProductOfCategoryByNameAsc(categoryId);
    }

    @GetMapping("/sort/{categoryId}/nameDesc")
    public List<Product> sortProductOfCategoryByNameDesc(@PathVariable int categoryId) {
        return productRepo.sortProductOfCategoryByNameDesc(categoryId);
    }

    @GetMapping("/sort/{categoryId}/priceAsc")
    public List<Product> sortProductOfCategoryByPriceAsc(@PathVariable int categoryId) {
        return productRepo.sortProductOfCategoryByPriceAsc(categoryId);
    }

    @GetMapping("/sort/{categoryId}/priceDesc")
    public List<Product> sortProductOfCategoryByPriceDesc(@PathVariable int categoryId) {
        return productRepo.sortProductOfCategoryByPriceDesc(categoryId);
    }
}
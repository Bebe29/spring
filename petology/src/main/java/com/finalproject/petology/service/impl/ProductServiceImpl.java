package com.finalproject.petology.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    // private String uploadProductPath = System.getProperty("user.dir")
    // + "\\src\\main\\resources\\static\\images\\products\\";
    private String uploadProductPath = System.getProperty("user.dir")
            + "\\petology\\src\\main\\resources\\static\\images\\products\\";

    @Override
    @Transactional
    public Iterable<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    @Transactional
    public Product addNewProducts(Product product, int categoryId) {
        Category findCategory = categoryRepo.findById(categoryId).get();

        if (findCategory == null)
            throw new RuntimeException("Category not found");

        product.setCategory(findCategory);

        return productRepo.save(product);
    }

    @Override
    @Transactional
    public String uploadImageProduct(MultipartFile file, String producString)
            throws JsonMappingException, JsonProcessingException {
        Date date = new Date();
        Product product = new ObjectMapper().readValue(producString, Product.class);
        // System.out.println(product);
        String fileExtension = file.getContentType().split("/")[1];

        String newFileName = "ProductName-" + product.getProductName() + "-" + date.getTime() + "." + fileExtension;

        String fileName = StringUtils.cleanPath(newFileName);

        Path path = Paths.get(StringUtils.cleanPath(uploadProductPath) + fileName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/products/download/")
                .path(fileName).toUriString();
        // product.setImage(fileDownloadUri);
        // productRepo.save(product);
        return fileDownloadUri;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> downloadImageProduct(String fileName) {
        Path path = Paths.get(uploadProductPath + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Override
    @Transactional
    public Product editProduct(Product product) {
        Product findProduct = productRepo.findById(product.getId()).get();
        if (findProduct == null)
            throw new RuntimeException("Product not found");

        return productRepo.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(int productId) {
        Product findProduct = productRepo.findById(productId).get();
        if (findProduct == null)
            throw new RuntimeException("Product not found with id:" + productId);

        if (findProduct.getCategory() == null) {
            productRepo.deleteById(productId);
        } else {
            Category findCategory = categoryRepo.findById(findProduct.getCategory().getId()).get();
            if (findCategory == null)
                throw new RuntimeException("Category not found");
            List<Product> productsCategory = findCategory.getProducts();
            productsCategory.remove(findProduct);
            findProduct.setCategory(null);

            productRepo.deleteById(productId);
        }
    }

    @Override
    @Transactional
    public Optional<Product> getProductById(int productId) {
        return productRepo.findById(productId);
    }

    @Override
    @Transactional
    public Iterable<Product> getPaginationDataProduct(int pageSize, int page) {
        int offset = (page - 1) * pageSize;
        return productRepo.getPaginationDataProduct(pageSize, offset);
    }

    // @Override
    // @Transactional
    // public Iterable<Product> filterProduct(String searchProduct) {
    // return productRepo.findProductByName(searchProduct);
    // }

    // @Override
    // @Transactional
    // public List<Product> sortProductByNameAsc() {
    // return productRepo.sortProductByNameAsc();
    // }

    // @Override
    // @Transactional
    // public List<Product> sortProductByNameDesc() {
    // return productRepo.sortProductByNameDesc();
    // }

    // @Override
    // @Transactional
    // public List<Product> sortProductByPriceAsc() {
    // return productRepo.sortProductByPriceAsc();
    // }

    // @Override
    // @Transactional
    // public List<Product> sortProductByPriceDesc() {
    // return productRepo.sortProductByPriceDesc();
    // }

    // @Override
    // @Transactional
    // public List<Product> sortProductOfCategoryByNameAsc(int categoryId) {
    // return productRepo.sortProductOfCategoryByNameAsc(categoryId);
    // }

    // @Override
    // @Transactional
    // public List<Product> sortProductOfCategoryByNameDesc(int categoryId) {
    // return productRepo.sortProductOfCategoryByNameDesc(categoryId);
    // }

    // @Override
    // @Transactional
    // public List<Product> sortProductOfCategoryByPriceAsc(int categoryId) {
    // return productRepo.sortProductOfCategoryByPriceAsc(categoryId);
    // }

    // @Override
    // @Transactional
    // public List<Product> sortProductOfCategoryByPriceDesc(int categoryId) {
    // return productRepo.sortProductOfCategoryByPriceDesc(categoryId);
    // }
}
package com.example.productservice.service;

import com.example.productservice.jpa.ComponentRepository;
import com.example.productservice.jpa.ProductRepository;
import com.example.productservice.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ComponentRepository componentRepository;
    private final ComponentService componentService = new ComponentService(componentRepository);
    private final ProductService productService = new ProductService(productRepository, componentService);

    Product findMe = new Product(1L, "TestProduct", null);

    @Test
    void findProductByIdSuccess() {
        productRepository.save(findMe);
        assertEquals(productService.findProductById(1L), findMe);
    }

    @Test
    void findProductByIdProductNotFound() {

    }

    @Test
    void findProductst() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void importProductsFromWarehouset() {
    }

    @Test
    void importDataFromWarehouste() {
    }
}
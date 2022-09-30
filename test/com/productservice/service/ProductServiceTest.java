package com.productservice.service;

import com.productservice.jpa.ComponentRepository;
import com.productservice.jpa.ProductRepository;
import com.productservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ComponentRepository componentRepository;

    private final ComponentService componentService = new ComponentService(this.componentRepository);
    private final ProductService productService = new ProductService(this.productRepository, this.componentService);

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
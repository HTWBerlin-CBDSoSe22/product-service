package com.example.productservice.service;

import com.example.productservice.model.Component;
import com.example.productservice.model.ComponentList;
import com.example.productservice.model.Product;
import com.example.productservice.model.ProductCreationRequest;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private final Component apple = new Component("Apple", 3.3, 3.3, 3.3, "green", "Germany", "grade", "category", "classification", "fall");
    private final ProductService productService = new ProductService();


    @Test
    void createProductHappy() {
        List<Component> componentList = new LinkedList<>();
        componentList.add(apple);
        ComponentList componentsForAppleSalad = new ComponentList(componentList);
        Product premadeAppleSalad = new Product("Apple Salad", componentsForAppleSalad);

        ProductCreationRequest request = new ProductCreationRequest(premadeAppleSalad.getName(), premadeAppleSalad.getConsistsOf());

        assertEquals(productService.createProduct(request).getName(),premadeAppleSalad.getName());
        assertEquals(productService.createProduct(request).getConsistsOf(),premadeAppleSalad.getConsistsOf());
    }


}
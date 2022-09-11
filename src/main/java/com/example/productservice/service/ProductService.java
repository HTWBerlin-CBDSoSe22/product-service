package com.example.productservice.service;

import com.example.productservice.model.ComponentList;
import com.example.productservice.model.Product;
import com.example.productservice.model.ProductCreationRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    //receives request consisting of desired name for product + components
    public Product createProduct(ProductCreationRequest request) {
        Product createdProduct = new Product(request.getProductName(), (ComponentList) request.getProductConsistsOf());
        return createdProduct;
    }

}

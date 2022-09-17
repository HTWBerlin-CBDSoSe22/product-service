package com.example.productservice.service;

import com.example.productservice.model.ComponentList;
import com.example.productservice.model.Product;
import com.example.productservice.model.*;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    //receives request consisting of desired name for product + components
    public Product createProduct(ProductMicroserviceDto request) {
        Product createdProduct = new Product(request.getName(), (ComponentList) request.getConsistsOf());
        return createdProduct;
    }

}

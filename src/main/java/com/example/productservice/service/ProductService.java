package com.example.productservice.service;

import com.example.productservice.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public Product createProduct(Product request) {
        if (request.getId() == null) {
            Product responseProduct = new Product(request.getId(), request.getName(), request.getConsistsOf());
        }
        // else wenn id nicht null ist in db nach der id suchen und entsprechendes prod ausgeben

        return null;
        //return responseProduct;
    }

}

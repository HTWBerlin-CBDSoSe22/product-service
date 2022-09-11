package com.example.productservice.model;

import java.util.List;

public class ProductCreationRequest {

    String productName;
    List<Component> productConsistsOf;

    public String getProductName() {
        return productName;
    }

    public List<Component> getProductConsistsOf() {
        return productConsistsOf;
    }

}

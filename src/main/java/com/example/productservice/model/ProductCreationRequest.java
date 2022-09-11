package com.example.productservice.model;

import java.util.List;

public class ProductCreationRequest {

    String productName;
    ComponentList productConsistsOf;

    public ProductCreationRequest(String productName, ComponentList productConsistsOf) {
        this.productName = productName;
        this.productConsistsOf = productConsistsOf;
    }
    public ProductCreationRequest() {

    }

    public String getProductName() {
        return productName;
    }

    public ComponentList getProductConsistsOf() {
        return productConsistsOf;
    }

}

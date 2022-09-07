package com.example.productservice.model;

import java.util.List;

public class ProductList {

    private List<Product> products;

    public ProductList(List<Product> products) {
        this.products = products;
    }
    public ProductList(){}

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

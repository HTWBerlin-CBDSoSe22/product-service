package com.example.productservice.model;

import java.util.HashSet;
import java.util.Set;

public class Product {

    private Long productId; //todo woher kommt die dann
    private Set<Component> consistsOf = new HashSet<Component>();
    public String name;

    public Product(String name, Set<Component> components) {
        this.name = name;
        this.consistsOf = components;
    }

    public Product() { }

    public Set<Component> getConsistsOf() {
        return consistsOf;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addComponent(Component component) {
        consistsOf.add(component);
    } //todo relevant?
}

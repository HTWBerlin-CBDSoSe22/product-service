package com.example.productservice.model;

import java.util.Set;

public class Product {

    public Product() { }

    public Product(Long id, String name, Set<Component> consistsOf) {
        this.id = id;
        this.name = name;
        this.consistsOf = consistsOf;
    }

    private Long id = null;
    private String name;
    private Set<Component> consistsOf;

    public String getName() {
        return name;
    }

    public Set<Component> getConsistsOf() {
        return consistsOf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConsistsOf(Set<Component> consistsOf) {
        this.consistsOf = consistsOf;
    }


}

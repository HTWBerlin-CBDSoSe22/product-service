package com.example.productservice.model;

import com.example.productservice.model.Component;

import java.util.Set;

public class ProductMicroserviceDto {
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

    public ProductMicroserviceDto() {
    }
}

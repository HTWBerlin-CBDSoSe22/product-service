package com.example.productservice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",updatable = false, nullable = false)
    private Long productId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_components",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private Set<Component> consistsOf = new HashSet<Component>();

    @Column(name = "name")
    public String name;

    public Product() {}

    public String getName() {
        return name;
    }

    public Set<Component> getConsistsOf() {
        return consistsOf;
    }

    public Long getId() {
        return productId;
    }

    public void setId(Long id) {
        this.productId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConsistsOf(Set<Component> consistsOf) {
        this.consistsOf = consistsOf;
    }

    public Product(Long id, String name, Set<Component> consistsOf) {
        this.productId = id;
        this.name = name;
        this.consistsOf = consistsOf;
    }

    public Product (String name, Set<Component> consistsOf) {
        this.productId = productId;
        this.name = name;
        this.consistsOf = consistsOf;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

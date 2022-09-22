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

    public String getName() {
        return name;
    }

    public Set<Component> getConsistsOf() {
        return consistsOf;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long id) {
        this.productId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConsistsOf(Set<Component> consistsOf) {
        this.consistsOf = consistsOf;
    }

    public Product() {
    }

    public Product(Long productId, String name, Set<Component> consistsOf) {
        this.productId = productId;
        this.name = name;
        this.consistsOf = consistsOf;
    }

    public Product (String name, Set<Component> consistsOf) {
        this.productId = productId;
        this.name = name;
        this.consistsOf = consistsOf;
    }
}

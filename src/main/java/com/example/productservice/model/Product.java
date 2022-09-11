package main.java.com.example.productservice.model;

public class Product {

    private ComponentList consistsOf;
    public String name;

    public Product(String name, ComponentList components) {
        this.name = name;
        this.consistsOf = components;
    }

    public Product() { }

    public ComponentList getConsistsOf() {
        return consistsOf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

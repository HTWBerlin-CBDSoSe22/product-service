package main.java.com.example.productservice.model;

import java.util.List;

public class ComponentList {

    private List<Component> components;

    public ComponentList(List<Component> components) {
        this.components = components;
    }

    public ComponentList(){
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> products) {
        this.components = components;
    }
}

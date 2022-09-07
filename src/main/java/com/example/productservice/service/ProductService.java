package com.example.productservice.service;

import com.example.productservice.model.Component;
import com.example.productservice.model.Product;
import com.example.productservice.model.ProductList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class ProductService {

    /*
    ● create 5 products with hardware components
    */
    Component strawberry = new Component("Strawberry", 0.12, 0.5, 0.5, "red", "Spanien", "H. extra", "dry", "Tropical fruit", "winter");
    Component raspberry = new Component("Raspberry", 0.12, 0.5, 0.5, "red", "Spanien", "H. extra", "dry", "Tropical fruit", "winter");
    Component cherry = new Component("Cherry", 0.12, 0.5, 0.5, "red", "Spanien", "H. extra", "dry", "Tropical fruit", "winter");
    Component pineapple = new Component("Pineapple", 0.12, 0.5, 0.5, "yellow", "Spanien", "H. extra", "dry", "Tropical fruit", "winter");
    Component banana = new Component("Pineapple", 0.12, 0.5, 0.5, "yellow", "Spanien", "H. extra", "dry", "Tropical fruit", "winter");
    Component grape = new Component("Grape", 0.12, 0.5, 0.5, "yellow", "Spanien", "H. extra", "dry", "Tropical fruit", "winter");
    Component mango = new Component("Mango", 0.12, 0.5, 0.5, "yellow", "Spanien", "H. extra", "dry", "Tropical fruit", "winter");

    Product redFruitSalad = new Product("Red fruit salad", new HashSet<Component>(){{add(strawberry);add(strawberry);add(raspberry);add(cherry);}});
    Product yellowFruitSalad = new Product("Yellow fruit salad", new HashSet<Component>(){{add(banana);add(banana);add(pineapple);add(grape);}});
    Product tropicalFruitSalad = new Product("Tropical fruit salad", new HashSet<Component>(){{add(banana);add(pineapple);add(mango);}});
    Product oneFruitySalad = new Product("One fruity salad", new HashSet<Component>(){{add(strawberry);add(raspberry);add(cherry);add(pineapple);add(mango);add(banana);add(grape);}});
    Product mangoFruitSalad = new Product("Fruit salad but really only mango", new HashSet<Component>(){{add(mango);add(mango);add(mango);add(mango);add(mango);add(mango);add(mango);add(mango);}});

    public ProductList createPremadeProductsList() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(redFruitSalad);
        productList.add(yellowFruitSalad);
        productList.add(tropicalFruitSalad);
        productList.add(oneFruitySalad);
        productList.add(mangoFruitSalad);

        ProductList premadeProducts = new ProductList();
        premadeProducts.setProducts(productList);

        return premadeProducts;
    }

    public void saveCreatedProducts() {

    }


    }

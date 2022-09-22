package com.example.productservice.service;

import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.jpa.ComponentRepository;
import com.example.productservice.jpa.ProductRepository;
import com.example.productservice.model.Component;
import com.example.productservice.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    public ProductService(ProductRepository productRepository, ComponentRepository componentRepository) {
        this.productRepository = productRepository;
        this.componentRepository = componentRepository;
    }

    private ProductRepository productRepository;

    public Product findProductById(Long idOfProduct) throws ResourceNotFoundException {
        Product foundProduct = null;
        foundProduct = productRepository.findById(idOfProduct).orElseThrow(() -> new ResourceNotFoundException("No Product with id " + idOfProduct));
        return foundProduct;
    }

    public List<Product> findProducts() throws ResourceNotFoundException {
        List<Product> products;
        products = (List<Product>) productRepository.findAll();
        if (products.size() < 1) {
            throw new ResourceNotFoundException("No components found");
        }
        return products;
    }

    public Product createProduct(Product request) {
        Product responseProduct = new Product();
        if (request.getId() == null) {
            responseProduct = new Product(request.getName(), request.getConsistsOf());
            productRepository.save(responseProduct);
        }
        return responseProduct;
    }

    @Scheduled(fixedRate = 5000)
    public void importDataFromWarehouse() throws IOException {
        importComponentsFromWarehouse();
        importProductsFromWarehouse();
        System.out.println("Scheduler Test: Das soll alle 5 Sekunden passieren");
    }

    public void importProductsFromWarehouse() throws IOException  {
        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url("http:localhost:8081/products")
                .build();
        Response response = okHttpClient.newCall(request).execute();

        String jsonString = Objects.requireNonNull(response.body()).string();
        System.out.println(jsonString);
        Product[] productsFromWarehouseArray = objectMapper.readValue(jsonString, Product[].class);
        List<Product> productsFromWarehouse = Arrays.asList(productsFromWarehouseArray);

        //Product receivedProduct = objectMapper.readValue(jsonString, Product.class);
        //productRepository.save(receivedProduct);
        productRepository.saveAll(productsFromWarehouse);
    }

    private ComponentRepository componentRepository;

    public Component findComponentById(Long idOfComponent) {
        Component foundComponent = null;
        foundComponent = componentRepository.findById(idOfComponent).orElseThrow(() -> new ResourceNotFoundException("No Product with id " + idOfComponent));
        return foundComponent;
    }

    public List<Component> findComponents() throws ResourceNotFoundException {
        List<Component> components;
        components = (List<Component>) componentRepository.findAll();
        if (components.size() < 1) {
            throw new ResourceNotFoundException("No components found");
        }
        return components;
    }

    public void importComponentsFromWarehouse() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url("http:localhost:8081/components")
                .build();
        Response response = okHttpClient.newCall(request).execute();

        String jsonString = Objects.requireNonNull(response.body()).string();
        System.out.println(jsonString);
        Component[] componentsFromWarehouseArray = objectMapper.readValue(jsonString, Component[].class);
        List<Component> componentsFromWarehouse = Arrays.asList(componentsFromWarehouseArray);

        componentRepository.saveAll(componentsFromWarehouse);
    }


}

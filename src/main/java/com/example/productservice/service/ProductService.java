package com.example.productservice.service;

import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.jpa.ProductRepository;
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
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public void getProductsFromWarehouse() throws IOException {
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

        productRepository.saveAll(productsFromWarehouse);
        System.out.println("Scheduler Test: Das soll alle 5 Sekunden passieren");
    }

}

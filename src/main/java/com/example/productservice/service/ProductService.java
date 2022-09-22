package com.example.productservice.service;

import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.jpa.ProductRepository;
import com.example.productservice.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ProductService(ProductRepository productRepository, ComponentService componentService) {
        this.productRepository = productRepository;
        this.componentService = componentService;
    }

    private ProductRepository productRepository;
    private ComponentService componentService;

    public Product findProductById(Long idOfProduct) throws ResourceNotFoundException {
        Product foundProduct = null;
        foundProduct = productRepository.findById(idOfProduct).orElseThrow(() -> new ResourceNotFoundException("No Product with id " + idOfProduct));
        return foundProduct;
    }

    public List<Product> findProducts() throws ResourceNotFoundException {
        List<Product> products;
        products = productRepository.findAll();
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

    @Scheduled(fixedRate = 180000)
    public void importDataFromWarehouse() throws IOException {
        componentService.importComponentsFromWarehouse();
        importProductsFromWarehouse();
        System.out.println("Scheduler: Data from Warehouse imported, next import in 3 minutes.");
    }

    public void importProductsFromWarehouse() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url("http:localhost:8081/products")
                .build();
        Response response = okHttpClient.newCall(request).execute();

        String jsonString = Objects.requireNonNull(response.body()).string();
        Product[] productsFromWarehouseArray = objectMapper.readValue(jsonString, Product[].class);
        List<Product> productsFromWarehouse = Arrays.asList(productsFromWarehouseArray);

        productRepository.saveAll(productsFromWarehouse);
    }

}

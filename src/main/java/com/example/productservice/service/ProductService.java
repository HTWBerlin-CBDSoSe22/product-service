package com.example.productservice.service;

import com.example.productservice.exception.CouldNotCreateException;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.jpa.ProductRepository;
import com.example.productservice.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
        products = (List<Product>) productRepository.findAll();
        if (products.size() < 1) {
            throw new ResourceNotFoundException("No products found");
        }
        return products;
    }

    public Product createProduct(Product request) throws CouldNotCreateException {
        Product responseProduct = new Product();
        if (request.getId() == null) {
            responseProduct = new Product(request.getName(), request.getConsistsOf());
            productRepository.save(responseProduct);
        } else {
            throw new CouldNotCreateException("Product could not be created");
        }
        return responseProduct;
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

    // PostConstruct enables initial execution on application start
    // cron job will be executed every day 1am Berlin time
    // second, minute, hour, day of month, month, day(s) of week
    @PostConstruct
    @Scheduled(cron = "0 0 1 * * *", zone="Europe/Berlin")
    public void importDataFromWarehouse() throws IOException {
        componentService.importComponentsFromWarehouse();
        importProductsFromWarehouse();
        System.out.println("Scheduler: Data from Warehouse imported, next import tomorrow 1am.");
    }
}

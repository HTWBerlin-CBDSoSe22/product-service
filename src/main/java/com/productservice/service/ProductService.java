package com.productservice.service;

import com.productservice.exception.CouldNotCreateException;
import com.productservice.exception.ResourceNotFoundException;
import com.productservice.exception.WarehouseNotReachableException;
import com.productservice.jpa.ProductRepository;
import com.productservice.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ConnectException;
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

    @Value("${warehouse.products.url}")
    private String url;

    private final ProductRepository productRepository;
    private final ComponentService componentService;

    public Product findProductById(Long idOfProduct) throws ResourceNotFoundException {
        Product foundProduct;
        foundProduct = productRepository.findById(idOfProduct).orElseThrow(() -> new ResourceNotFoundException("No Product with id " + idOfProduct));
        return foundProduct;
    }

    public List<Product> findProducts() throws ResourceNotFoundException {
        List<Product> foundProducts;
        foundProducts = productRepository.findAll();
        if (foundProducts.size() < 1) {
            throw new ResourceNotFoundException("No products found");
        }
        return foundProducts;
    }

    public Product createProduct(Product request) throws CouldNotCreateException {
        Product newProduct = new Product();
        if (request.getProductId() == null) {
            newProduct = new Product(request.getName(), request.getConsistsOf());
            productRepository.save(newProduct);
        } else {
            throw new CouldNotCreateException("Product could not be created");
        }
        return newProduct;
    }

    public void importProductsFromWarehouse() throws IOException, WarehouseNotReachableException {
        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url(this.url)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String jsonString = Objects.requireNonNull(response.body()).string();
            Product[] productsFromWarehouseArray = objectMapper.readValue(jsonString, Product[].class);
            List<Product> productsFromWarehouse = Arrays.asList(productsFromWarehouseArray);
            productRepository.saveAll(productsFromWarehouse);
        } catch (ConnectException e) {
            throw new WarehouseNotReachableException("Warehouse not reachable for component import");
        }
    }

    // PostConstruct enables initial execution on application start
    // cron job will be executed every day 1am Berlin time
    // second, minute, hour, day of month, month, day(s) of week
    @PostConstruct
    @Scheduled(cron = "0 0 1 * * *", zone = "Europe/Berlin")
    public void importDataFromWarehouse() throws IOException {
        try {
            componentService.importComponentsFromWarehouse();
            importProductsFromWarehouse();
            System.out.println("Scheduler: Data from Warehouse imported, next import tomorrow 1am.");
        } catch (WarehouseNotReachableException e) {
            e.printStackTrace();
        }
    }

}

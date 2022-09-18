package com.example.productservice.service;

import com.example.productservice.jpa.ComponentRepository;
import com.example.productservice.jpa.ProductRepository;
import com.example.productservice.model.Product;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static ProductRepository productRepository;

    public Product findProductById(Long idOfProduct) {
        Product foundProduct = null;
        if (productRepository.findById(idOfProduct).isPresent()) {
            foundProduct = productRepository.findById(idOfProduct).get();
        }
        return foundProduct;
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product request) {
        Product responseProduct = new Product();
        if (request.getId() == null) {
             responseProduct = new Product(request.getId(), request.getName(), request.getConsistsOf());
             productRepository.save(responseProduct);
        }
        return responseProduct;
    }

    @Scheduled(fixedRate = 5000)
    public void getFromWarehouse() {
        System.out.println("Scheduler Test: Das soll alle 5 Sekunden passieren");
    }

}

package com.example.productservice.service;

import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.jpa.ProductRepository;
import com.example.productservice.model.Product;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static ProductRepository productRepository;

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
            responseProduct = new Product(request.getId(), request.getName(), request.getConsistsOf());
            //responseProduct.setId(); todo set to was
            productRepository.save(responseProduct);
        }
        return responseProduct;
    }

    @Scheduled(fixedRate = 5000)
    public void getFromWarehouse() {
        System.out.println("Scheduler Test: Das soll alle 5 Sekunden passieren");
    }

}

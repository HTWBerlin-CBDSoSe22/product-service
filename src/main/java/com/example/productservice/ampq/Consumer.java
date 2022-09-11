package com.example.productservice.ampq;

import com.example.productservice.model.Product;
import com.example.productservice.model.ProductCreationRequest;
import com.example.productservice.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    ProductService productService;

    @RabbitListener(queues = "#{queue.name}", returnExceptions = "true")
    public Product handleRequest(ProductCreationRequest request)  {
        System.out.println("Receiving request to create product ");
        return productService.createProduct(request);
    }

}
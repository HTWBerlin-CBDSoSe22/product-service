package com.example.productservice.ampq;

import com.example.productservice.model.Product;
import com.example.productservice.model.ProductMicroserviceDto;
import com.example.productservice.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Consumer {

    @Autowired
    ProductService productService;

    @RabbitListener(queues = "#{productQueue.name}", returnExceptions = "true")
    public Product handleProductCreationRequest(ProductMicroserviceDto productMicroserviceDto)  {
        System.out.println("Receiving request to create product ");
        return productService.createProduct(productMicroserviceDto);
    }
    @RabbitListener(queues = "#{componentQueue.name}", returnExceptions = "true")
    public List<com.example.productservice.model.Component> handleComponentInformationRequest(String message)  {
        System.out.println("Receiving request to fetch component data ");

        return new ArrayList<com.example.productservice.model.Component>();
    }

}
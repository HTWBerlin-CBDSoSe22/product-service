package com.example.productservice.ampq;

import com.example.productservice.model.Product;
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

    @RabbitListener(queues = "#{singleProductQueue.name}")
    public Product handleProductCreationRequest(Product product)  {
        Product createdOrFoundProduct = product;
        if(product.getId() == null) {
//todo        createdOrFoundProduct = productService.createProduct(product);
        }else {
//todo        createdOrFoundProduct = productService.findProduct(product.getId());
        }
        return createdOrFoundProduct;
    }
    @RabbitListener(queues = "#{componentQueue.name}")
    public List<com.example.productservice.model.Component> handleComponentInformationRequest(String message)  {
        List<com.example.productservice.model.Component> listOfComponents = new ArrayList<>();
        if(message.equals("showComponents")) {
            //  todo fetch components
            listOfComponents.add(new com.example.productservice.model.Component("Berry",1, 0.5, 12, "red",
                    "Germany", "H. I", "Good",
                    "classification", "summer"));
        }else {
            try {
                Long.parseLong(message);
                //  todo fetch component by id
                listOfComponents.add(new com.example.productservice.model.Component("Banana",1, 0.5, 12, "red",
                "Germany", "H. I", "Good",
                "classification", "summer"));
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return listOfComponents;
    }

    @RabbitListener(queues = "#{allProductsQueue.name}")
    public List<Product> handleFetchAllProductsRequest(String message) {
        System.out.println(message);
        //TODO fetch all products from db
        return new ArrayList<>();
    }

}
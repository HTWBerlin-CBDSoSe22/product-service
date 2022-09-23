package com.example.productservice.ampq;

import com.example.productservice.model.Product;
import com.example.productservice.service.ComponentService;
import com.example.productservice.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Consumer {
    @Autowired
    private ProductService productService;
    @Autowired
    private ComponentService componentService;

    @RabbitListener(queues = "#{singleProductQueue.name}", returnExceptions = "true")
    public Product handleProductCreationRequest(Product product)  {
        Product createdOrFoundProduct;
        if(this.checkCreationOrFetchRequest(product)) {
            createdOrFoundProduct = this.productService.createProduct(product);
        }else {
            createdOrFoundProduct = this.productService.findProductById(product.getProductId());
        }
        return createdOrFoundProduct;
    }
    @RabbitListener(queues = "#{componentQueue.name}", returnExceptions = "true")
    public List<com.example.productservice.model.Component> handleComponentInformationRequest(String message)  {
        List<com.example.productservice.model.Component> listOfComponents = new ArrayList<>();
        if(message.equals("showComponents")) {
            listOfComponents = this.componentService.findComponents();
        }else {
            try {
                Long componentId = Long.parseLong(message);
                com.example.productservice.model.Component foundComponent = this.componentService.findComponentById(componentId);
                listOfComponents.add(foundComponent);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return listOfComponents;
    }

    @RabbitListener(queues = "#{allProductsQueue.name}", returnExceptions = "true")
    public List<Product> handleFetchAllProductsRequest() {
        List<Product> foundProducts = this.productService.findProducts();
        return foundProducts;
    }

    private boolean checkCreationOrFetchRequest(Product receivedProduct) {
        boolean shouldBeCreated = (receivedProduct.getProductId() == null);
        return shouldBeCreated;
    }
}
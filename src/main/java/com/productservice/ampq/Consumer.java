package com.productservice.ampq;

import com.productservice.exception.CouldNotCreateException;
import com.productservice.model.Product;
import com.productservice.service.ComponentService;
import com.productservice.service.ProductService;
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
    public Product handleProductCreationRequest(Product product) throws CouldNotCreateException {
        Product requestedProduct;
        if (this.notYetCreated(product)) {
            requestedProduct = this.productService.createProduct(product);
        } else {
            requestedProduct = this.productService.findProductById(product.getProductId());
        }
        return requestedProduct;
    }

    @RabbitListener(queues = "#{componentQueue.name}", returnExceptions = "true")
    public List<com.productservice.model.Component> handleComponentInformationRequest(String message) {
        List<com.productservice.model.Component> listOfComponents = new ArrayList<>();
        if (message.equals("showComponents")) {
            listOfComponents = this.componentService.findComponents();
        } else {
            try {
                Long componentId = Long.parseLong(message);
                com.productservice.model.Component foundComponent = this.componentService.findComponentById(componentId);
                listOfComponents.add(foundComponent);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return listOfComponents;
    }

    @RabbitListener(queues = "#{allProductsQueue.name}", returnExceptions = "true")
    public List<Product> handleFetchAllProductsRequest() {
        return this.productService.findProducts();
    }

    private boolean notYetCreated(Product product) {
        return (product.getProductId() == null);
    }
}
package com.example.productservice.ampq;

import com.example.productservice.service.PriceService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    PriceService priceService;

    /*
    @RabbitListener(queues = "#{queue.name}", returnExceptions = "true")
    public ProductPrice handleRequest(ComponentPrices request) throws ComponentPricesNotFoundException, FloatingPointOverflowException {
        System.out.println("Receiving: " + request.toString());
        return priceService.calculateProductPrice(request);
    }
    */
}
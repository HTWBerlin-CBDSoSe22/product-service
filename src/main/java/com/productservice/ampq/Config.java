package com.productservice.ampq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class Config {

    private final String ROUTING_KEY_SINGLE_PRODUCT = "createProduct";

    private final String ROUTING_KEY_ALL_PRODUCTS = "getAllProducts";

    private final String ROUTING_KEY_COMPONENTS = "getInformation";

    private final String DIRECT_EXCHANGE = "itemExchange";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Queue componentQueue() {
        return new Queue("componentQueue");
    }

    @Bean
    public Queue singleProductQueue() {
        return new Queue("singleProductQueue");
    }

    @Bean
    public Queue allProductsQueue() {
        return new Queue("allProductsQueue");
    }

    @Bean
    public Binding bindingSingleProductQueueToExchange(DirectExchange directExchange, Queue singleProductQueue) {
        return BindingBuilder.bind(singleProductQueue)
                .to(directExchange)
                .with(ROUTING_KEY_SINGLE_PRODUCT);
    }

    @Bean
    public Binding bindingAllProductsQueueToExchange(DirectExchange directExchange, Queue allProductsQueue) {
        return BindingBuilder.bind(allProductsQueue)
                .to(directExchange)
                .with(ROUTING_KEY_ALL_PRODUCTS);
    }

    @Bean
    public Binding bindingComponentsQueueExchange(DirectExchange directExchange,
                                                  Queue componentQueue) {
        return BindingBuilder.bind(componentQueue)
                .to(directExchange)
                .with(ROUTING_KEY_COMPONENTS);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
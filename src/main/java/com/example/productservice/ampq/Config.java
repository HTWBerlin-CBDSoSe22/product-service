package com.example.productservice.ampq;

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

    private final String ROUTING_KEY = "createProduct";
    private final String DIRECT_EXCHANGE = "itemExchange";
//    private final String QUEUE_NAME = "calculatePriceQueue";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }
    @Bean
    public Queue componentQueue() {
        return new Queue("componentQueue");
    }
    @Bean
    public Queue productQueue() {
        return new Queue("productQueue");
    }
    @Bean
    public Binding binding(DirectExchange directExchange, Queue productQueue) {
        return BindingBuilder.bind(productQueue())
                .to(directExchange)
                .with(ROUTING_KEY);
    }
    @Bean
    public Binding binding2(DirectExchange directExchange,
                            Queue componentQueue) {
        return BindingBuilder.bind(componentQueue)
                .to(directExchange)
                .with("getInformation");
    }
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
package com.example.productservice.service;

import com.example.productservice.jpa.ComponentRepository;
import com.example.productservice.model.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComponentService {

    private static ComponentRepository componentRepository;
    
    public Optional<Component> findProduct(Long idOfProduct) {
        Optional<Component> foundProduct = null;
        if (componentRepository.findById(idOfProduct).isPresent()) {
            foundProduct = componentRepository.findById(idOfProduct);
        }
        return foundProduct;
    }

    public List<Component> findComponents() {
        return (List<Component>) componentRepository.findAll();
    }
}
package com.example.productservice.service;

import com.example.productservice.jpa.ComponentRepository;
import com.example.productservice.model.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    private static ComponentRepository componentRepository;

    public Component findComponentById(Long idOfComponent) {
        Component foundComponent = null;
        if (componentRepository.findById(idOfComponent).isPresent()) {
            foundComponent = componentRepository.findById(idOfComponent).get();
        }
        return foundComponent;
    }

    public List<Component> findComponents() {
        return (List<Component>) componentRepository.findAll();
    }
}

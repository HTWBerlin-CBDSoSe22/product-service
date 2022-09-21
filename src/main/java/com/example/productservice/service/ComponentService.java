package com.example.productservice.service;

import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.jpa.ComponentRepository;
import com.example.productservice.model.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    private ComponentRepository componentRepository;

    public Component findComponentById(Long idOfComponent) {
        Component foundComponent = null;
        foundComponent = componentRepository.findById(idOfComponent).orElseThrow(() -> new ResourceNotFoundException("No Product with id " + idOfComponent));
        return foundComponent;
    }

    public List<Component> findComponents() throws ResourceNotFoundException {
        List<Component> components;
        components = (List<Component>) componentRepository.findAll();
        if (components.size() < 1) {
            throw new ResourceNotFoundException("No components found");
        }
        return components;
    }
}

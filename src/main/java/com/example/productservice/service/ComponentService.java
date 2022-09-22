package com.example.productservice.service;

import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.jpa.ComponentRepository;
import com.example.productservice.model.Component;
import com.example.productservice.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public void getComponentsFromWarehouse() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url("http:localhost:8081/components")
                .build();
        Response response = okHttpClient.newCall(request).execute();

        String jsonString = Objects.requireNonNull(response.body()).string();
        System.out.println(jsonString);
        Component[] componentsFromWarehouseArray = objectMapper.readValue(jsonString, Component[].class);
        List<Component> componentsFromWarehouse = Arrays.asList(componentsFromWarehouseArray);

        componentRepository.saveAll(componentsFromWarehouse);
    }
}

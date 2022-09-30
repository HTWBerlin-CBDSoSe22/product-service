package com.productservice.service;

import com.productservice.exception.ResourceNotFoundException;
import com.productservice.exception.WarehouseNotReachableException;
import com.productservice.jpa.ComponentRepository;
import com.productservice.model.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ComponentService {

    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Value("${warehouse.components.url}")
    private String url;

    private final ComponentRepository componentRepository;

    public Component findComponentById(Long idOfComponent) throws ResourceNotFoundException {
        Component foundComponent;
        foundComponent = componentRepository.findById(idOfComponent).orElseThrow(() -> new ResourceNotFoundException("No Component with id " + idOfComponent));
        return foundComponent;
    }

    public List<Component> findComponents() throws ResourceNotFoundException {
        List<Component> foundComponents;
        foundComponents = (List<Component>) componentRepository.findAll();
        if (foundComponents.size() < 1) {
            throw new ResourceNotFoundException("No components found");
        }
        return foundComponents;
    }

    public void importComponentsFromWarehouse() throws IOException, WarehouseNotReachableException {
        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url(this.url)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String jsonString = Objects.requireNonNull(response.body()).string();
            Component[] componentsFromWarehouseArray = objectMapper.readValue(jsonString, Component[].class);
            List<Component> componentsFromWarehouse = Arrays.asList(componentsFromWarehouseArray);
            componentRepository.deleteAll();
            componentRepository.saveAll(componentsFromWarehouse);
        } catch (ConnectException e) {
            throw new WarehouseNotReachableException("Warehouse not found");
        }

    }
}

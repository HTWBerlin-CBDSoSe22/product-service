package com.example.productservice.jpa;

import com.example.productservice.model.Component;
import org.springframework.data.repository.CrudRepository;

public interface ComponentRepository extends CrudRepository<Component, Long> {

}

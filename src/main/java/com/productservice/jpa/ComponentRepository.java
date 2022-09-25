package com.productservice.jpa;

import com.productservice.model.Component;
import org.springframework.data.repository.CrudRepository;

public interface ComponentRepository extends CrudRepository<Component, Long> {

}

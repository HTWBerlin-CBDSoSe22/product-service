package main.java.com.example.productservice.jpa;

import main.java.com.example.productservice.model.Component;
import org.springframework.data.repository.CrudRepository;

public interface ComponentRepository extends CrudRepository<Component, Long> {

}

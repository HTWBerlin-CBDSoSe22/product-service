package com.example.productservice.exception;

public class ResourceNotFoundException extends NullPointerException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

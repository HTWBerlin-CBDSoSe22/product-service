package com.example.productservice.exception;

public class CouldNotCreateException extends InstantiationException  {
    public CouldNotCreateException(String message) {
        super(message);
    }
}

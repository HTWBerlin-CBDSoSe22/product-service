package com.productservice.exception;

public class CouldNotCreateException extends InstantiationException  {
    public CouldNotCreateException(String message) {
        super(message);
    }
}

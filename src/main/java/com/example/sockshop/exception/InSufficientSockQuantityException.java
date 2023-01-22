package com.example.sockshop.exception;

public class InSufficientSockQuantityException extends RuntimeException {

    public InSufficientSockQuantityException(String message) {
        super(message);
    }
}

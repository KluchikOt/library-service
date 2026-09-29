package com.example.library.exception;


public class EmptyBookFilterException extends RuntimeException {
    public EmptyBookFilterException(String message) {
        super(message);
    }
}
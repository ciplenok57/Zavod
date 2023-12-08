package com.example.demo.exceprion;

public class OperationStopException extends RuntimeException{
    public OperationStopException(String message) {
        super(message);
    }
}

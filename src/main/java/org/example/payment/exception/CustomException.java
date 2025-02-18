package org.example.payment.exception;

import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException{
    private final String details;

    public CustomException(String message, String details) {
        super(message);
        this.details = details;
    }
}


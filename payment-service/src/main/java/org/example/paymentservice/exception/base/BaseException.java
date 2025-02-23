package org.example.paymentservice.exception.base;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException{
    private final String details;

    public BaseException(String message, String details) {
        super(message);
        this.details = details;
    }
}

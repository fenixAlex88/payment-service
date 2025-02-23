package org.example.paymentservice.exception.custom;

import org.example.paymentservice.exception.base.BaseException;

public class UserNotFoundException extends BaseException{

    public UserNotFoundException(Long userId) {
        super("User not found for ID: " + userId, "User with specified ID not found");
    }
}

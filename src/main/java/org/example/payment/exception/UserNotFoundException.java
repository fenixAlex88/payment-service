package org.example.payment.exception;

public class UserNotFoundException extends CustomException{

    public UserNotFoundException(Long userId) {
        super("User not found for ID: " + userId, "User with specified ID not found");
    }
}

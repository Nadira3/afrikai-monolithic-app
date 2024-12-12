package com.precious.AfrikAI.exception;

// Custom exception for user registration
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

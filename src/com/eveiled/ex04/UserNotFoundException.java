package com.eveiled.ex04;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super();
    }
}


package com.eveiled.ex05.transaction;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException() {
        super();
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
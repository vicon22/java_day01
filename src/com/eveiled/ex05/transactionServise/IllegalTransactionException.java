package com.eveiled.ex05.transactionServise;

public class IllegalTransactionException extends RuntimeException{

    public IllegalTransactionException() {
        super();
    }

    public IllegalTransactionException(String message) {
        super(message);
    }
}

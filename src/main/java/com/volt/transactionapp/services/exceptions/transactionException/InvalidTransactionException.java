package com.volt.transactionapp.services.exceptions.transactionException;

public class InvalidTransactionException extends RuntimeException{
    public InvalidTransactionException(String message) {
        super(message);
    }
}

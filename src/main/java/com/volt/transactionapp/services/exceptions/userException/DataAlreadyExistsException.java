package com.volt.transactionapp.services.exceptions.userException;

public class DataAlreadyExistsException extends RuntimeException{
    public DataAlreadyExistsException(String message) {
        super(message);
    }
}

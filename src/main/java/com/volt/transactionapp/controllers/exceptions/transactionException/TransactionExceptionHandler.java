package com.volt.transactionapp.controllers.exceptions.transactionException;

import com.volt.transactionapp.controllers.exceptions.DefaultException;
import com.volt.transactionapp.services.exceptions.generalException.ResourceNotFoundException;
import com.volt.transactionapp.services.exceptions.transactionException.InvalidTransactionException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class TransactionExceptionHandler extends RuntimeException{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DefaultException> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
        DefaultException error = new DefaultException();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Resource Not Found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<DefaultException> invalidTransaction(InvalidTransactionException e, HttpServletRequest request){
        DefaultException error = new DefaultException();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Invalid transaction");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}

package com.volt.transactionapp.controllers.exceptions.userException;

import com.volt.transactionapp.controllers.exceptions.DefaultException;
import com.volt.transactionapp.services.exceptions.generalException.ResourceNotFoundException;
import com.volt.transactionapp.services.exceptions.userException.DataAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class UserExceptionHandler extends RuntimeException{
    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<DefaultException> dataAlreadyExists(DataAlreadyExistsException e, HttpServletRequest request){
        DefaultException error = new DefaultException();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Data already exists");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}

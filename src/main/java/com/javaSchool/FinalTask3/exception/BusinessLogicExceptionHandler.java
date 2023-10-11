package com.javaSchool.FinalTask3.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class BusinessLogicExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<?> handleConflict(ResourceConflictException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    // TODO Bad practice. Use placeholder instead.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknown(Exception e){
        String errorId = UUID.randomUUID().toString();

        return ResponseEntity.status(500).body("Server error occurred, pleased contact the admin. Error ID is " + errorId);
    }
}

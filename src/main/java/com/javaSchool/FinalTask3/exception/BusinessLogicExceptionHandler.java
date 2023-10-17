package com.javaSchool.FinalTask3.exception;

import com.javaSchool.FinalTask3.utils.StringValues;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

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

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<?> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e){
        return ResponseEntity.status(415).body(StringValues.ACCEPTABLE_MEDIA + MediaType.APPLICATION_JSON_VALUE);
    }
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknown(Exception e){
        String errorId = UUID.randomUUID().toString();

        return ResponseEntity.status(500).body(StringValues.SERVER_ERROR + errorId);
    }*/
}

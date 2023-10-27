package com.javaSchool.FinalTask3.exception;

import com.javaSchool.FinalTask3.utils.StringValues;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessLogicExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(ResourceConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> handleConflict(ResourceConflictException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<?> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e){
        return ResponseEntity.status(415).body(StringValues.ACCEPTABLE_MEDIA + MediaType.APPLICATION_JSON_VALUE);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException e){
        return ResponseEntity.status(403).body("Roles check failed, access denied");
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknown(Exception e){
        String errorId = UUID.randomUUID().toString();

        return ResponseEntity.status(500).body(StringValues.SERVER_ERROR + errorId);
    }*/
}

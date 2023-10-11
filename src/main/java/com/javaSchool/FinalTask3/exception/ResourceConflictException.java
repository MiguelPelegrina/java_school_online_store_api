package com.javaSchool.FinalTask3.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResourceConflictException extends RuntimeException{
    private final String message;

    @Override
    public String getMessage(){
        return message;
    }
}

package com.javaSchool.FinalTask3.exception;

import lombok.RequiredArgsConstructor;

/**
 * A custom exception class for resource conflict errors.
 * Extends the {@link RuntimeException} class.
 */
@RequiredArgsConstructor
public class ResourceConflictException extends RuntimeException{
    private final String message;

    /**
     * Overrides the getMessage method to provide the error message.
     * @return Error message associated with the exception.
     */
    @Override
    public String getMessage(){
        return message;
    }
}

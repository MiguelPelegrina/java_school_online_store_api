package com.javaSchool.FinalTask3.exception;

import com.javaSchool.FinalTask3.utils.StringValues;

/**
 * A custom exception class for insufficient permissions.
 * Extends the {@link RuntimeException} class.
 */
public class InsufficientPermissions extends RuntimeException{
    /**
     * Overrides the getMessage method to provide the error message.
     * @return Error message associated with the exception.
     */
    @Override
    public String getMessage(){
        return StringValues.INSUFFICIENT_PERMISSIONS;
    }
}
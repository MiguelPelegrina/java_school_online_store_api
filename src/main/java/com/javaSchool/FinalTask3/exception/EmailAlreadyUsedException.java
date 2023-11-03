package com.javaSchool.FinalTask3.exception;

import com.javaSchool.FinalTask3.utils.StringValues;

/**
 * A custom exception class for emails already in use errors.
 * Extends the {@link RuntimeException} class.
 */
public class EmailAlreadyUsedException extends RuntimeException {
    /**
     * Overrides the getMessage method to provide the error message.
     * @return Error message associated with the exception.
     */
    @Override
    public String getMessage(){
        return StringValues.EMAIL_ALREADY_IN_USE;
    }
}

package com.javaSchool.FinalTask3.exception.user;

import com.javaSchool.FinalTask3.utils.StringValues;
import lombok.RequiredArgsConstructor;

/**
 * A custom exception class for not existing users.
 * Extends the {@link RuntimeException} class.
 */
@RequiredArgsConstructor
public class UserDoesNotExistException extends RuntimeException{
    private final String message;

    /**
     * Overrides the getMessage method to provide the error message.
     * @return Error message associated with the exception.
     */
    @Override
    public String getMessage(){
        return String.format(StringValues.USER_DOES_NOT_EXIST, message);
    }
}

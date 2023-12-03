package com.java_school.final_task.exception.user;

import com.java_school.final_task.utils.StringValues;

/**
 * A custom exception class for inactive users.
 * Extends the {@link RuntimeException} class.
 */
public class InactiveUserException extends RuntimeException {
    /**
     * Overrides the getMessage method to provide the error message.
     *
     * @return Error message associated with the exception.
     */
    @Override
    public String getMessage() {
        return StringValues.INACTIVE_USER;
    }
}
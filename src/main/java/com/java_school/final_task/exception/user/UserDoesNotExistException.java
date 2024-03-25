package com.java_school.final_task.exception.user;

import lombok.RequiredArgsConstructor;

/**
 * A custom exception class for not existing users.
 * Extends the {@link RuntimeException} class.
 */
@RequiredArgsConstructor
public class UserDoesNotExistException extends RuntimeException {
    private final String mail;

    /**
     * Overrides the getMessage method to provide the error message.
     *
     * @return Error message associated with the exception.
     */
    @Override
    public String getMessage() {
        return mail;
    }
}

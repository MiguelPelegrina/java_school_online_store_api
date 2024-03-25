package com.java_school.final_task.exception;

import com.java_school.final_task.utils.StringValues;

/**
 * A messaging exception wrapper.
 * This checked exception is wrapped to unchecked to handle the exception in the {@link BusinessLogicExceptionHandler}
 * Extends the {@link RuntimeException} class.
 */
public class MessagingExceptionWrapper extends RuntimeException {

    /**
     * Overrides the getMessage method to provide the error message.
     *
     * @return Error message associated with the exception.
     */
    @Override
    public String getMessage() {
        return StringValues.MAIL_SENDING_ERROR;
    }
}

package com.java_school.final_task.exception;

import com.java_school.final_task.utils.StringValues;
import lombok.RequiredArgsConstructor;

/**
 * A custom exception class for resource not found errors.
 * Extends the {@link RuntimeException} class.
 */
@RequiredArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    // Fields
    private final String id;

    /**
     * Overrides the getMessage method to provide the error message.
     *
     * @return Error message associated with the exception.
     */
    @Override
    public String getMessage() {
        return String.format(StringValues.INSTANCE_NOT_FOUND, id);
    }
}

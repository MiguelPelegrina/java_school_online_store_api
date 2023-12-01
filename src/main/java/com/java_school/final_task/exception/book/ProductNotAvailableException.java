package com.java_school.final_task.exception.book;

import com.java_school.final_task.utils.StringValues;
import lombok.RequiredArgsConstructor;

/**
 * A custom exception class for not available.
 * Extends the {@link RuntimeException} class.
 */
@RequiredArgsConstructor
public class ProductNotAvailableException extends RuntimeException {
    private final String title;

    /**
     * Overrides the getMessage method to provide the error message.
     *
     * @return Error message associated with the exception.
     */
    @Override
    public String getMessage() {
        return String.format(StringValues.PRODUCT_NOT_AVAILABLE, title);
    }
}

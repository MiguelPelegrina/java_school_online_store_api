package com.javaSchool.FinalTask3.exception.book;

import com.javaSchool.FinalTask3.domain.book.BookEntity;
import com.javaSchool.FinalTask3.utils.StringValues;
import lombok.RequiredArgsConstructor;

/**
 * A custom exception class for not available.
 * Extends the {@link RuntimeException} class.
 */
@RequiredArgsConstructor
public class ProductNotAvailableException extends RuntimeException{
    private final BookEntity book;

    /**
     * Overrides the getMessage method to provide the error message.
     * @return Error message associated with the exception.
     */
    @Override
    public String getMessage(){
        return String.format(StringValues.PRODUCT_NOT_AVAILABLE, book.getTitle());
    }
}

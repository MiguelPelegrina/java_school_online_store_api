package com.javaSchool.FinalTask3.domain.book;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

/**
 * RestController interface of the {@link BookEntity} entity. Handles the REST methods. Uses {@link BookDTO} as returning object.
 */
public interface BookRestController {
    /**
     * Retrieves a page of {@link BookDTO}s from the database based on {@link BookRequest}
     * @param bookRequest  {@link BookRequest} that contains all the specified parameters and sorting criteria.
     * @return             ResponseEntity containing a Page of {@link BookDTO}s based on the specified criteria.
     */
     ResponseEntity<Page<BookDTO>> getAllInstances(BookRequest bookRequest);
}

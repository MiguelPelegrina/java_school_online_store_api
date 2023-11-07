package com.javaSchool.FinalTask3.domain.book;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * RestController interface of the {@link BookEntity} entity. Handles the REST methods. Uses {@link BookDTO} as returning object.
 */
public interface BookRestController {
    /**
     * Retrieves a page of {@link BookDTO}s from the database based on specified parameters and sorting criteria.
     * @param name          The name or title to search for.
     * @param active        An optional flag indicating whether books should be active or not.
     * @param genre         The genre of the books to search for.
     * @param sortType      The sorting direction.
     * @param sortProperty  The property by which to sort the results.
     * @param page          The page number to retrieve.
     * @param size          The number of items per page.
     * @return              ResponseEntity containing a Page of {@link BookDTO}s based on the specified criteria.
     */
     ResponseEntity<Page<BookDTO>> getAllInstances(
            String name,
            Optional<Boolean> active,
            String genre,
            String sortType,
            String sortProperty,
            Integer page,
            Integer size
     );
}

package com.javaSchool.FinalTask3.domain.book;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * RestController interface of the {@link BookEntity} entity. Handles the REST methods. Uses {@link BookDTO} as returning object.
 */
public interface BookRestController {
    /**
     * Retrieves a page of BookDTO objects from the database based on specified parameters and sorting criteria.
     * @param name          The name or title to search for (case-insensitive). Default is an empty string.
     * @param active        An optional flag indicating whether books should be active or not.
     * @param genre         The genre of the books to search for (case-insensitive). Default is an empty string.
     * @param sortType      The sorting direction (either "ASC" or "DESC"). Default is "ASC".
     * @param sortProperty  The property by which to sort the results. Default is "title".
     * @param page          The page number to retrieve. Default is 0.
     * @param size          The number of items per page. Default is 20.
     * @return              ResponseEntity containing a Page of BookDTO objects based on the specified criteria.
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

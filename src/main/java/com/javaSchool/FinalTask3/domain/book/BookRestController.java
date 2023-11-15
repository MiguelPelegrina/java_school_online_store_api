package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.domain.book.dto.BookDTO;
import com.javaSchool.FinalTask3.domain.book.dto.NumberedBookDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

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

    /**
     * Retrieves the top products based on a specified limit.
     * @param limit The maximum number of top products to retrieve.
     * @return      ResponseEntity containing a list of {@link NumberedBookDTO} representing the top products.
     */
     ResponseEntity<List<NumberedBookDTO>> getTopProducts(int limit);
}

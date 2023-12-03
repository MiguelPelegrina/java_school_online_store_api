package com.java_school.final_task.domain.book;

import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.dto.NumberedBookDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * RestController interface of the {@link BookEntity} entity. Handles the REST methods. Uses {@link BookDTO} as returning object.
 */
public interface BookRestController {
    /**
     * Saves a list of {@link BookEntity} instances to the database.
     * Returns a ResponseEntity containing a list of corresponding {@link BookDTO}s.
     *
     * @param instances A list of {@link BookEntity} instances to be saved to the database.
     * @return ResponseEntity containing a list of the saved {@link BookDTO}s.
     */
    ResponseEntity<List<BookDTO>> saveInstances(List<BookEntity> instances);

    /**
     * Retrieves a page of {@link BookDTO}s from the database based on {@link BookRequest}
     *
     * @param bookRequest {@link BookRequest} that contains all the specified parameters and sorting criteria.
     * @return ResponseEntity containing a Page of {@link BookDTO}s based on the specified criteria.
     */
    ResponseEntity<Page<BookDTO>> getAllInstances(BookRequest bookRequest);

    /**
     * Retrieves the top products based on a specified limit.
     *
     * @param limit The maximum number of top products to retrieve.
     * @return ResponseEntity containing a list of {@link NumberedBookDTO} representing the top products.
     */
    ResponseEntity<List<NumberedBookDTO>> getTopProducts(int limit);
}

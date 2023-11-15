package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.domain.book.dto.BookDTO;
import com.javaSchool.FinalTask3.domain.book.dto.NumberedBookDTO;
import com.javaSchool.FinalTask3.domain.book.impl.BookRestControllerImpl;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Service interface responsible for the interaction between the {@link BookRepository} and the
 * {@link BookRestControllerImpl}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link BookEntity} as {@link BookDTO} to the {@link BookRestControllerImpl}.
 */
public interface BookService {
    /**
     * Retrieves a page of {@link BookDTO}s from the database based on {@link BookRequest}.
     * @param bookRequest   {@link BookRequest} that contains all the specified parameters and sorting criteria.
     * @return               ResponseEntity containing a Page of {@link BookDTO}}s based on the specified criteria.
     */
    Page<BookDTO> getAllInstances(BookRequest bookRequest);

    /**
     * Retrieves the top products based on the total order amounts.
     * @param limit The maximum number of top products to retrieve.
     * @return A list of {@link NumberedBookDTO} representing the top products.
     */
    List<NumberedBookDTO> getTopProducts(int limit);
}

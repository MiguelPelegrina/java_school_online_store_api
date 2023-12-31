package com.java_school.final_task.domain.book;

import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.dto.BookRequestDTO;
import com.java_school.final_task.domain.book.dto.NumberedBookDTO;
import com.java_school.final_task.domain.book.impl.BookRestControllerImpl;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Service interface responsible for the interaction between the {@link BookRepository} and the
 * {@link BookRestControllerImpl}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link BookEntity} as {@link BookDTO} to the {@link BookRestControllerImpl}.
 */
public interface BookService {
    /**
     * Saves a list of {@link BookEntity} instances to the database and returns a list of corresponding {@link BookDTO}s.
     *
     * @param books A list of {@link BookEntity} instances to be saved to the database.
     * @return A list of {@link BookDTO}s representing the saved entities.
     */
    List<BookDTO> saveInstances(List<BookEntity> books);

    /**
     * Retrieves a page of {@link BookDTO}s from the database based on {@link BookRequestDTO}.
     *
     * @param bookRequestDTO {@link BookRequestDTO} that contains all the specified parameters and sorting criteria.
     * @return ResponseEntity containing a Page of {@link BookDTO}}s based on the specified criteria.
     */
    Page<BookDTO> getAllInstances(BookRequestDTO bookRequestDTO);

    /**
     * Retrieves the top products based on the total order amounts.
     *
     * @param limit The maximum number of top products to retrieve.
     * @return A list of {@link NumberedBookDTO} representing the top products.
     */
    List<NumberedBookDTO> getTopProducts(int limit);
}

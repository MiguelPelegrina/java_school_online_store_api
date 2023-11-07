package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.domain.book.impl.BookRestControllerImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

/**
 * Service interface responsible for the interaction between the {@link BookRepository} and the
 * {@link BookRestControllerImpl}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link BookEntity} as {@link BookDTO} to the {@link BookRestControllerImpl}.
 */
public interface BookService {
    /**
     * Retrieves a page of {@link BookDTO}s from the database based on specified parameters.
     * @param name          The name or title to search for.
     * @param active        An optional flag indicating whether books should be active or not.
     *                      If present, filters books based on the provided value.
     * @param genre         The genre of the books to search for.
     * @param pageRequest   PageRequest object specifying page number, page size, and sorting criteria.
     * @return              Page of {@link BookDTO}s matching the specified criteria.
     */
    Page<BookDTO> getAllInstances(String name, Optional<Boolean> active, String genre, PageRequest pageRequest);
}

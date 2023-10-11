package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookEntity} entity. Handles the REST methods. Uses {@link BookDTO} as returning object.
 */
@RequestMapping(path = "books")
@RestController
public class BookRestController extends AbstractRestControllerWithUpdate<BookEntity, BookDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link BookService} of the {@link BookEntity} entity.
     */
    public BookRestController(AbstractServiceWithUpdate<BookEntity, BookDTO, Integer> service) {
        super(service);
    }
}

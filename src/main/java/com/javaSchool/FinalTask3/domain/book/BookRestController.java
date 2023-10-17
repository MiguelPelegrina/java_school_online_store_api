package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookEntity} entity. Handles the REST methods. Uses {@link BookDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "books")
@RestController
public class BookRestController extends AbstractRestController<BookEntity, BookDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link BookService} of the {@link BookEntity} entity.
     */
    public BookRestController(AbstractService<BookEntity, BookDTO, Integer> service) {
        super(service);
    }
}

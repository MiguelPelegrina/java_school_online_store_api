package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookEntity} entity. Handles the REST methods. Uses {@link BookDTO} as returning object.
 */
@RequestMapping(path = "books")
@RestController
@Secured("ROLE_ADMIN")
public class BookRestControllerImpl extends AbstractRestControllerImpl<BookEntity, BookDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link BookServiceImpl} of the {@link BookEntity} entity.
     */
    public BookRestControllerImpl(AbstractServiceImpl<BookEntity, BookDTO, Integer> service) {
        super(service);
    }
}

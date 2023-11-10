package com.javaSchool.FinalTask3.domain.book.impl;

import com.javaSchool.FinalTask3.domain.book.*;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookEntity} entity. Handles the REST methods. Uses {@link BookDTO} as returning object.
 */
@RequestMapping(path = "books")
@RestController
public class BookRestControllerImpl
        extends AbstractRestControllerImpl<BookServiceImpl, BookRepository, BookEntity, BookDTO, Integer>
        implements BookRestController {
    /**
     * All arguments constructor.
     * @param service {@link BookServiceImpl} of the {@link BookEntity} entity.
     */
    public BookRestControllerImpl(BookServiceImpl service) {
        super(service);
    }

    // TODO Not scalable, does not allow sorting more than one time or filtering more than one genre
    @GetMapping("/search")
    @Override
    public ResponseEntity<Page<BookDTO>> getAllInstances(BookRequest bookRequest) {
        return ResponseEntity.ok(this.service.getAllInstances(bookRequest));
    }
}

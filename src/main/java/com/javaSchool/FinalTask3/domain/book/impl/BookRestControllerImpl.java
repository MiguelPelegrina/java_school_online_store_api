package com.javaSchool.FinalTask3.domain.book.impl;

import com.javaSchool.FinalTask3.domain.book.BookDTO;
import com.javaSchool.FinalTask3.domain.book.BookEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> getAllInstances(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "active") Optional<Boolean> active,
            @RequestParam(name = "sort") Optional<String> sort,
            @RequestParam(name = "page") Optional<Integer> page,
            @RequestParam(name = "size") Optional<Integer> size
    ) {
        BookServiceImpl bookService = (BookServiceImpl) this.service;

        return ResponseEntity.ok(bookService.getAllInstances(name, active, sort, page, size));
    }
}

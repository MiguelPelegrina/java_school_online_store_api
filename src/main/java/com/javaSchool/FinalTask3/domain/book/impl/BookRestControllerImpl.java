package com.javaSchool.FinalTask3.domain.book.impl;

import com.javaSchool.FinalTask3.domain.book.BookDTO;
import com.javaSchool.FinalTask3.domain.book.BookEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

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


    // TODO Not scalable, does not allow sorting more than one time
    @GetMapping("/search")
    public ResponseEntity<Page<BookDTO>> getAllInstances(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam("active") Optional<Boolean> active,
            @RequestParam("sortType") String sortType,
            @RequestParam(value = "sortProperty", defaultValue = "title") String sortProperty,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
        BookServiceImpl bookService = (BookServiceImpl) this.service;

        // TODO Not sure if this should be here or in the service
        // Check the sorting direction
        Sort.Direction direction = ASC.toString().equalsIgnoreCase(sortType)? ASC : DESC;

        PageRequest pageRequest = PageRequest.of(page, size, direction, sortProperty);

        return ResponseEntity.ok(bookService.getAllInstances(name, active, pageRequest));
    }
}

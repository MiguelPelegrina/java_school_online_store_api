package com.javaSchool.FinalTask3.domain.book.impl;

import com.javaSchool.FinalTask3.domain.book.BookDTO;
import com.javaSchool.FinalTask3.domain.book.BookEntity;
import com.javaSchool.FinalTask3.domain.book.BookRepository;
import com.javaSchool.FinalTask3.domain.book.BookRestController;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

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
    public ResponseEntity<Page<BookDTO>> getAllInstances(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam("active") Optional<Boolean> active,
            @RequestParam(name = "genre", defaultValue = "") String genre,
            @RequestParam("sortType") String sortType,
            @RequestParam(name = "sortProperty", defaultValue = "title") String sortProperty,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size
    ) {
        // Check the sorting direction
        Sort.Direction direction = DESC.toString().equalsIgnoreCase(sortType) ? DESC : ASC;

        PageRequest pageRequest = PageRequest.of(page, size, direction, sortProperty);

        return ResponseEntity.ok(this.service.getAllInstances(name, active, genre, pageRequest));
    }
}

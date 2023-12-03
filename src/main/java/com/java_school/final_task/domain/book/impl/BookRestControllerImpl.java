package com.java_school.final_task.domain.book.impl;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.BookRepository;
import com.java_school.final_task.domain.book.BookRequest;
import com.java_school.final_task.domain.book.BookRestController;
import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.dto.NumberedBookDTO;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     *
     * @param service {@link BookServiceImpl} of the {@link BookEntity} entity.
     */
    public BookRestControllerImpl(BookServiceImpl service) {
        super(service);
    }

    @Override
    @PostMapping("/save_all")
    public ResponseEntity<List<BookDTO>> saveInstances(@RequestBody List<BookEntity> instances) {
        List<BookDTO> instanceDTOs = service.saveInstances(instances);

        if (instanceDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(instanceDTOs, HttpStatus.CREATED);
        }
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<Page<BookDTO>> getAllInstances(BookRequest bookRequest) {
        return ResponseEntity.ok(this.service.getAllInstances(bookRequest));
    }

    @GetMapping("/top_products")
    @Override
    public ResponseEntity<List<NumberedBookDTO>> getTopProducts(@RequestParam("limit") int limit) {
        return ResponseEntity.ok(this.service.getTopProducts(limit));
    }
}

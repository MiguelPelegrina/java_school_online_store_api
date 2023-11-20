package com.java_school.final_task.domain.book.impl;

import com.javaSchool.finalTask.domain.book.*;
import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.BookRepository;
import com.java_school.final_task.domain.book.BookRequest;
import com.java_school.final_task.domain.book.BookRestController;
import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.dto.NumberedBookDTO;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    // TODO Add a number to the dto
    // TODO Implement Paging and sorting
    @GetMapping("/top_products")
    @Override
    public ResponseEntity<List<NumberedBookDTO>> getTopProducts(
            @RequestParam("limit") int limit
            //@RequestParam("page") int page,
            //@RequestParam("size") int size
    ){
        return ResponseEntity.ok(this.service.getTopProducts(limit
                //, page, size
                ));
    }
}

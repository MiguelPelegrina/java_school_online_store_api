package com.java_school.final_task.domain.book.parameter.impl;

import com.java_school.final_task.domain.book.parameter.BookParameterDTO;
import com.java_school.final_task.domain.book.parameter.BookParameterEntity;
import com.java_school.final_task.domain.book.parameter.BookParameterRepository;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookParameterEntity} entity. Handles the REST methods. Uses {@link BookParameterDTO} as
 * returning object.
 */
@RequestMapping(path = "book_parameters")
@RestController
public class BookParameterRestControllerImpl
        extends AbstractRestControllerImpl<BookParameterServiceImpl, BookParameterRepository, BookParameterEntity, BookParameterDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link BookParameterServiceImpl} of the {@link BookParameterEntity} entity.
     */
    public BookParameterRestControllerImpl(BookParameterServiceImpl service) {
        super(service);
    }
}

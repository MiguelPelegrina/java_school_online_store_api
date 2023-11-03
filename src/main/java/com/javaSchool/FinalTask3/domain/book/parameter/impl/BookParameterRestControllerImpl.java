package com.javaSchool.FinalTask3.domain.book.parameter.impl;

import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterDTO;
import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterEntity;
import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
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

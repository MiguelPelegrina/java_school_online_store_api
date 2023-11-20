package com.java_school.final_task.domain.book.parameter.format.impl;

import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatDTO;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatEntity;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatRepository;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookParametersFormatEntity} entity. Handles the REST methods. Uses
 * {@link BookParametersFormatDTO} as returning object.
 */
@RequestMapping(path = "book_parameters_format")
@RestController
public class BookParametersFormatRestControllerImpl
        extends AbstractRestControllerImpl<BookParametersFormatServiceImpl, BookParametersFormatRepository, BookParametersFormatEntity, BookParametersFormatDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link BookParametersFormatServiceImpl} of the {@link BookParametersFormatEntity} entity.
     */
    public BookParametersFormatRestControllerImpl(BookParametersFormatServiceImpl service){
        super(service);
    }
}

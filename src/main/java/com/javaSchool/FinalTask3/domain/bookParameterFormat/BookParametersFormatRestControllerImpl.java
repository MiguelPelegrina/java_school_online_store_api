package com.javaSchool.FinalTask3.domain.bookParameterFormat;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookParametersFormatEntity} entity. Handles the REST methods. Uses
 * {@link BookParametersFormatDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "book_parameters_format")
@RestController
public class BookParametersFormatRestControllerImpl extends AbstractRestControllerImpl<BookParametersFormatEntity, BookParametersFormatDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link BookParametersFormatServiceImpl} of the {@link BookParametersFormatEntity} entity.
     */
    public BookParametersFormatRestControllerImpl(AbstractServiceImpl<BookParametersFormatEntity, BookParametersFormatDTO, String> service){
        super(service);
    }
}

package com.javaSchool.FinalTask3.domain.bookParameterFormat;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookParametersFormatEntity} entity. Handles the REST methods. Uses
 * {@link BookParametersFormatDTO} as returning object.
 */
@RequestMapping(path = "bookparametersformat", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class BookParametersFormatRestController extends AbstractRestController<BookParametersFormatEntity, BookParametersFormatDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link BookParametersFormatService} of the {@link BookParametersFormatEntity} entity.
     */
    public BookParametersFormatRestController(AbstractService<BookParametersFormatEntity, BookParametersFormatDTO, String> service){
        super(service);
    }
}

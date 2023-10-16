package com.javaSchool.FinalTask3.domain.bookParameter;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link BookParameterEntity} entity. Handles the REST methods. Uses {@link BookParameterDTO} as
 * returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "bookparameters")
@RestController
public class BookParameterRestController extends AbstractRestController<BookParameterEntity, BookParameterDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link BookParameterService} of the {@link BookParameterEntity} entity.
     */
    public BookParameterRestController(AbstractService<BookParameterEntity, BookParameterDTO, Integer> service) {
        super(service);
    }
}

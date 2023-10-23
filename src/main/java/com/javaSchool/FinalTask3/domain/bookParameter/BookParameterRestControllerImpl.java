package com.javaSchool.FinalTask3.domain.bookParameter;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
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
public class BookParameterRestControllerImpl extends AbstractRestControllerImpl<BookParameterEntity, BookParameterDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link BookParameterServiceImpl} of the {@link BookParameterEntity} entity.
     */
    public BookParameterRestControllerImpl(AbstractServiceImpl<BookParameterEntity, BookParameterDTO, Integer> service) {
        super(service);
    }
}

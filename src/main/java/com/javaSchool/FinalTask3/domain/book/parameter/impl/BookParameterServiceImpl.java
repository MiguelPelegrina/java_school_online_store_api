package com.javaSchool.FinalTask3.domain.book.parameter.impl;

import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterDTO;
import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterEntity;
import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for the interaction between the {@link BookParameterRepository} and the
 * {@link BookParameterRestControllerImpl}. Obtains data from the
 * {@link BookParameterRepository} and returns the object(s) of the entity {@link BookParameterEntity} as
 * {@link BookParameterDTO} to the {@link BookParameterRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
@Service
public class BookParameterServiceImpl
        extends AbstractServiceImpl<BookParameterRepository, BookParameterEntity, BookParameterDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link BookParameterRepository} of the {@link BookParameterEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookParameterEntity} to {@link BookParameterDTO}
     */
    public BookParameterServiceImpl(BookParameterRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }
    @Override
    public Class<BookParameterDTO> getDTOClass() {
        return null;
    }

    @Override
    public Integer getEntityId(BookParameterEntity instance) {
        return instance.getId();
    }
}

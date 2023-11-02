package com.javaSchool.FinalTask3.domain.book.parameter.impl;

import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterDTO;
import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterEntity;
import com.javaSchool.FinalTask3.domain.book.parameter.BookParameterRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link BookParameterRepository} and the
 * {@link BookParameterRestControllerImpl}. Obtains data from the
 * {@link BookParameterRepository} and returns the object(s) of the entity {@link BookParameterEntity} as
 * {@link BookParameterDTO} to the {@link BookParameterRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class BookParameterServiceImpl extends AbstractServiceImpl<BookParameterEntity, BookParameterDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link BookParameterRepository} of the {@link BookParameterEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookParameterEntity} to {@link BookParameterDTO}
     */
    public BookParameterServiceImpl(BookParameterRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link BookParameterEntity} entity.
     * @return Returns the {@link BookParameterDTO} class.
     */
    @Override
    protected Class<BookParameterDTO> getDTOClass() {
        return null;
    }

    @Override
    protected Integer getEntityId(BookParameterEntity instance) {
        return instance.getId();
    }
}

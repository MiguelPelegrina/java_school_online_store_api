package com.java_school.final_task.domain.book.parameter.format.impl;

import com.java_school.final_task.domain.book.genre.BookGenreRepository;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatDTO;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatEntity;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatRepository;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for the interaction between the {@link BookParametersFormatRepository} and the
 * {@link BookParametersFormatRestControllerImpl}. Obtains data from the
 * {@link BookParametersFormatRepository} and returns the object(s) of the entity {@link BookParametersFormatEntity} as
 * {@link BookParametersFormatDTO} to the {@link BookParametersFormatRestControllerImpl}.
 */
@Service
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
public class BookParametersFormatServiceImpl
        extends AbstractServiceImpl<BookParametersFormatRepository, BookParametersFormatEntity, BookParametersFormatDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link BookGenreRepository} of the {@link BookParametersFormatEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookParametersFormatEntity} to {@link BookParametersFormatDTO}
     */
    public BookParametersFormatServiceImpl(BookParametersFormatRepository repository, ModelMapper modelMapper){
        super(repository, modelMapper);
    }

    @Override
    public Class<BookParametersFormatDTO> getDTOClass() {
        return BookParametersFormatDTO.class;
    }

    @Override
    public String getEntityId(BookParametersFormatEntity instance) {
        return instance.getName();
    }
}

package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookParametersFormatDTO;
import com.javaSchool.FinalTask3.entities.BookParametersFormat;
import com.javaSchool.FinalTask3.repositories.BookGenreRepository;
import com.javaSchool.FinalTask3.repositories.BookParametersFormatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link BookParametersFormatRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.BookParametersFormatController}. Obtains data from the
 * {@link BookParametersFormatRepository} and returns the object(s) of the entity {@link BookParametersFormat} as
 * {@link BookParametersFormatDTO} to the {@link com.javaSchool.FinalTask3.controller.BookParametersFormatController}.
 */
@Service
@Transactional(readOnly = true)
public class BookParametersFormatService extends BaseService<BookParametersFormat, BookParametersFormatDTO, String>{
    /**
     * All arguments constructor.
     * @param repository {@link BookGenreRepository} of the {@link BookParametersFormat} entity.
     * @param modelMapper ModelMapper that converts the {@link BookParametersFormat} to {@link BookParametersFormatDTO}
     */
    public BookParametersFormatService(BookParametersFormatRepository repository, ModelMapper modelMapper){
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link BookParametersFormat} entity.
     * @return Returns the {@link BookParametersFormatDTO} class.
     */
    @Override
    protected Class<BookParametersFormatDTO> getDTOClass() {
        return BookParametersFormatDTO.class;
    }
}

package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookParameterDTO;
import com.javaSchool.FinalTask3.entities.BookParameter;
import com.javaSchool.FinalTask3.repositories.BookParameterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link BookParameterRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.BookParameterController}. Obtains data from the
 * {@link BookParameterRepository} and returns the object(s) of the entity {@link BookParameter} as
 * {@link BookParameterDTO} to the {@link com.javaSchool.FinalTask3.controller.BookParameterController}.
 */
@Service
@Transactional(readOnly = true)
public class BookParameterService extends BaseServiceWithUpdate<BookParameter, BookParameterDTO, Integer>{
    /**
     * All arguments constructor.
     * @param repository {@link BookParameterRepository} of the {@link BookParameter} entity.
     * @param modelMapper ModelMapper that converts the {@link BookParameter} to {@link com.javaSchool.FinalTask3.dtos.BookParameterDTO}
     */
    public BookParameterService(BookParameterRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link BookParameter} entity.
     * @return Returns the {@link BookParameterDTO} class.
     */
    @Override
    protected Class<BookParameterDTO> getDTOClass() {
        return null;
    }

    /**
     * Updates the values of an existing instance of {@link BookParameter}.
     * @param existingItem Instance of {@link BookParameter} that already exists in the database.
     * @param newItem Instance of {@link BookParameter} that stores the new values to update the existing instance with.
     */
    @Override
    protected void updateValues(BookParameter existingItem, BookParameter newItem) {
        existingItem.setActive(newItem.isActive());
        existingItem.setAuthor(newItem.getAuthor());
        existingItem.setFormat(newItem.getFormat());
    }
}

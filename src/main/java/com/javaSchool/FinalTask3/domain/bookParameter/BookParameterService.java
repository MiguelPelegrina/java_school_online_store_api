package com.javaSchool.FinalTask3.domain.bookParameter;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link BookParameterRepository} and the
 * {@link BookParameterRestController}. Obtains data from the
 * {@link BookParameterRepository} and returns the object(s) of the entity {@link BookParameterEntity} as
 * {@link BookParameterDTO} to the {@link BookParameterRestController}.
 */
@Service
@Transactional(readOnly = true)
public class BookParameterService extends AbstractServiceWithUpdate<BookParameterEntity, BookParameterDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link BookParameterRepository} of the {@link BookParameterEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookParameterEntity} to {@link BookParameterDTO}
     */
    public BookParameterService(BookParameterRepository repository, ModelMapper modelMapper) {
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

    /**
     * Updates the values of an existing instance of {@link BookParameterEntity}.
     * @param existingItem Instance of {@link BookParameterEntity} that already exists in the database.
     * @param newItem Instance of {@link BookParameterEntity} that stores the new values to update the existing instance with.
     */
    @Override
    protected void updateValues(BookParameterEntity existingItem, BookParameterEntity newItem) {
        existingItem.setActive(newItem.isActive());
        existingItem.setAuthor(newItem.getAuthor());
        existingItem.setFormat(newItem.getFormat());
    }
}

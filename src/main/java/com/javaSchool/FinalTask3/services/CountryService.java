package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.CountryDTO;
import com.javaSchool.FinalTask3.entities.Country;
import com.javaSchool.FinalTask3.repositories.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO Not sure if I should try to implement the Spring transaction routing mentioned in
//  https://vladmihalcea.com/read-write-read-only-transaction-routing-spring/
/**
 * Service class responsible for the interaction between the {@link CountryRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.CountryController}. Obtains data from the
 * {@link CountryRepository} and returns the object(s) of the entity {@link Country} as
 * {@link CountryDTO} to the {@link com.javaSchool.FinalTask3.controller.CountryController}.
 */
@Service
@Transactional(readOnly = true)
public class CountryService extends BaseServiceWithUpdate<Country, CountryDTO, String>{
    /**
     * All arguments constructor.
     * @param repository {@link CountryRepository} of the {@link Country} entity.
     * @param modelMapper ModelMapper that converts the {@link Country} to {@link CountryDTO}
     */
    public CountryService(CountryRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link Country} entity.
     * @return Returns the {@link CountryDTO} class.
     */
    @Override
    protected Class<CountryDTO> getDTOClass() {
        return CountryDTO.class;
    }

    /**
     * Updates the values of an existing {@link Country} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(Country existingInstance, Country newInstance) {
        existingInstance.setActive(newInstance.isActive());
    }
}

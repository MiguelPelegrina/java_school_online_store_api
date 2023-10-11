package com.javaSchool.FinalTask3.domain.country;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO Not sure if I should try to implement the Spring transaction routing mentioned in
//  https://vladmihalcea.com/read-write-read-only-transaction-routing-spring/
/**
 * Service class responsible for the interaction between the {@link CountryRepository} and the
 * {@link CountryRestController}. Obtains data from the
 * {@link CountryRepository} and returns the object(s) of the entity {@link CountryEntity} as
 * {@link CountryDTO} to the {@link CountryRestController}.
 */
@Service
@Transactional(readOnly = true)
public class CountryService extends AbstractServiceWithUpdate<CountryEntity, CountryDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link CountryRepository} of the {@link CountryEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link CountryEntity} to {@link CountryDTO}
     */
    public CountryService(CountryRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link CountryEntity} entity.
     * @return Returns the {@link CountryDTO} class.
     */
    @Override
    protected Class<CountryDTO> getDTOClass() {
        return CountryDTO.class;
    }

    /**
     * Updates the values of an existing {@link CountryEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(CountryEntity existingInstance, CountryEntity newInstance) {
        existingInstance.setActive(newInstance.isActive());
    }
}

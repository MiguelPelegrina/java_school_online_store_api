package com.javaSchool.FinalTask3.domain.city;

import com.javaSchool.FinalTask3.domain.country.CountryRepository;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link CityRepository} and the
 * {@link CityController}. Obtains data from the
 * {@link CityRepository} and returns the object(s) of the entity {@link CityEntity} as
 * {@link CityDTO} to the {@link CityController}.
 */
@Service
@Transactional(readOnly = true)
public class CityService extends AbstractServiceWithUpdate<CityEntity, CityDTO, String> {
    private final CountryRepository countryRepository;
    /**
     * All arguments constructor.
     *
     * @param repository        {@link CityRepository} of the {@link CityEntity} entity.
     * @param modelMapper       ModelMapper that converts the {@link CityRepository} to {@link CityDTO}
     * @param countryRepository {@link CountryRepository} of an entity City relies upon
     */
    public CityService(CityRepository repository, ModelMapper modelMapper, CountryRepository countryRepository) {
        super(repository, modelMapper);
        this.countryRepository = countryRepository;
    }

    // TODO Can it be done differently?
    /**
     * Handles the POST request. Saves an instance of the entity into the database.
     * @param instance Instance of the entity that will be saved.
     * @return Returns the DTO. If the instance could not be saved, it returns null.
     */
    @Override
    @Transactional
    public CityDTO saveInstance(CityEntity instance){
        instance.setCountry(countryRepository.getReferenceById(instance.getCountry().getName()));
        return modelMapper.map(repository.save(instance), getDTOClass());
    }

    /**
     * Returns the DTO class of the {@link CityEntity} entity.
     * @return Returns the {@link CityDTO} class.
     */
    @Override
    protected Class<CityDTO> getDTOClass() {
        return CityDTO.class;
    }

    /**
     * Updates the values of an existing {@link CityEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(CityEntity existingInstance, CityEntity newInstance) {
        existingInstance.setActive(newInstance.isActive());
        existingInstance.setCountry(newInstance.getCountry());
    }
}

package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.CityDTO;
import com.javaSchool.FinalTask3.entities.City;
import com.javaSchool.FinalTask3.repositories.CityRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link CityRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.CityController}. Obtains data from the
 * {@link CityRepository} and returns the object(s) of the entity {@link City} as
 * {@link CityDTO} to the {@link com.javaSchool.FinalTask3.controller.CityController}.
 */
@Service
@Transactional(readOnly = true)
public class CityService extends BaseServiceWithUpdate<City, CityDTO, String>{
    /**
     * All arguments constructor.
     * @param repository {@link CityRepository} of the {@link City} entity.
     * @param modelMapper ModelMapper that converts the {@link CityRepository} to {@link CityDTO}
     */
    public CityService(CityRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link City} entity.
     * @return Returns the {@link CityDTO} class.
     */
    @Override
    protected Class<CityDTO> getDTOClass() {
        return CityDTO.class;
    }

    /**
     * Updates the values of an existing {@link City} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(City existingInstance, City newInstance) {
        existingInstance.setActive(newInstance.isActive());
        existingInstance.setCountry(newInstance.getCountry());
    }
}

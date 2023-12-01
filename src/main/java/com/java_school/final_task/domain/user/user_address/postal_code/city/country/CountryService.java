package com.java_school.final_task.domain.user.user_address.postal_code.city.country;

import com.java_school.final_task.domain.user.user_address.postal_code.city.country.impl.CountryRestControllerImpl;

import java.util.List;
import java.util.Optional;

/**
 * Service interface responsible for the interaction between the {@link CountryRepository} and the
 * {@link CountryRestControllerImpl}. Obtains data from the
 * {@link CountryRepository} and returns the object(s) of the entity {@link CountryEntity} as
 * {@link CountryDTO} to the {@link CountryRestControllerImpl}.
 */
public interface CountryService {
    /**
     * Retrieves a list of CountryDTOs from the database based on specified parameters.
     *
     * @param active      An optional flag indicating whether countries should be active or not.
     * @param countryName The name of the country (case-insensitive).
     * @return List of CountryDTOs matching the specified criteria.
     */
    List<CountryDTO> getAllInstances(Optional<Boolean> active, String countryName);
}

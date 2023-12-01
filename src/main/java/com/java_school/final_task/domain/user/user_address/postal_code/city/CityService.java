package com.java_school.final_task.domain.user.user_address.postal_code.city;

import com.java_school.final_task.domain.user.user_address.postal_code.city.impl.CityRestControllerImpl;

import java.util.List;
import java.util.Optional;

/**
 * Service interface responsible for the interaction between the {@link CityRepository} and the
 * {@link CityRestControllerImpl}. Obtains data from the
 * {@link CityRepository} and returns the object(s) of the entity {@link CityEntity} as
 * {@link CityDTO} to the {@link CityRestControllerImpl}.
 */
public interface CityService {
    /**
     * Retrieves a list of CityDTO objects from the database based on specified parameters.
     *
     * @param countryName The name of the country associated with the cities (case-insensitive).
     * @param active      An optional flag indicating whether cities should be active or not.
     * @return A list of CityDTO objects matching the specified criteria.
     */
    List<CityDTO> getAllInstances(String countryName, Optional<Boolean> active);
}

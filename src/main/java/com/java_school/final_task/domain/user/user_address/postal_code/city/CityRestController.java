package com.java_school.final_task.domain.user.user_address.postal_code.city;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * RestController interface of the {@link CityEntity} entity. Handles the REST methods. Uses
 * {@link CityDTO} as returning object.
 */
public interface CityRestController {
    /**
     * Retrieves a list of CityDTO objects from the database based on specified parameters.
     *
     * @param countryName The name of the country associated with the cities (case-insensitive). Default is an empty string.
     * @param active      An optional flag indicating whether cities should be active or not.
     * @return ResponseEntity containing a list of CityDTO objects matching the specified criteria.
     */
    ResponseEntity<List<CityDTO>> getAllInstances(
            String countryName,
            Optional<Boolean> active
    );
}

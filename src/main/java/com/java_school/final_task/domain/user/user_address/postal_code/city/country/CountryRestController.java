package com.java_school.final_task.domain.user.user_address.postal_code.city.country;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * RestController interface of the {@link CountryEntity} entity. Handles the REST methods. Uses {@link CountryDTO} as
 * returning object.
 */
public interface CountryRestController {
    /**
     * Retrieves a list of CountryDTOs from the database based on specified parameters.
     *
     * @param active      An optional flag indicating whether countries should be active or not.
     * @param countryName The name of the country (case-insensitive). Default is an empty string.
     * @return A ResponseEntity containing a list of CountryDTO objects matching the specified criteria.
     */
    ResponseEntity<List<CountryDTO>> getAllInstances(Optional<Boolean> active, String countryName);
}

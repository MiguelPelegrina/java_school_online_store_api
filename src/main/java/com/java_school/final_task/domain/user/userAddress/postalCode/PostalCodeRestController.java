package com.java_school.final_task.domain.user.userAddress.postalCode;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * RestController interface of the {@link PostalCodeEntity} entity. Handles the REST methods. Uses
 * {@link PostalCodeDTO} as returning object.
 */
public interface PostalCodeRestController {
    /**
     * Retrieves a list of PostalCodeDTO objects from the database based on specified parameters.
     *
     * @param cityName       The name of the city (case-insensitive). Default is an empty string.
     * @param active        An optional flag indicating whether postal codes should be active or not.
     * @return              ResponseEntity containing a list of PostalCodeDTO objects matching the specified criteria.
     */
    ResponseEntity<List<PostalCodeDTO>> getAllInstances(String cityName, Optional<Boolean> active);
}

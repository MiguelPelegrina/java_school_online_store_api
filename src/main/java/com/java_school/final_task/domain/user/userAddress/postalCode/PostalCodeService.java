package com.java_school.final_task.domain.user.userAddress.postalCode;

import com.java_school.final_task.domain.user.userAddress.postalCode.impl.PostalCodeRestControllerImpl;

import java.util.List;
import java.util.Optional;

/**
 * Service interface responsible for the interaction between the {@link PostalCodeRepository} and the {@link PostalCodeRestControllerImpl}.
 * Obtains data from the  {@link PostalCodeRepository} and returns the object(s) of the entity {@link PostalCodeEntity} as
 * {@link PostalCodeDTO} to the {@link PostalCodeRestControllerImpl}.
 */
public interface PostalCodeService {
    /**
     * Retrieves a list of PostalCodeDTO objects from the database based on specified parameters.
     *
     * @param cityName       The name of the city (case-insensitive).
     * @param active        An optional flag indicating whether postal codes should be active or not.
     * @return              A list of PostalCodeDTO objects matching the specified criteria.
     */
    List<PostalCodeDTO> getAllInstances(String cityName, Optional<Boolean> active);
}

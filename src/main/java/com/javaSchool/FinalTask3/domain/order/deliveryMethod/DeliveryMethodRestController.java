package com.javaSchool.FinalTask3.domain.order.deliveryMethod;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * RestController interface of the {@link DeliveryMethodEntity} entity. Handles the REST methods. Uses
 * {@link DeliveryMethodDTO} as returning object.
 */
public interface DeliveryMethodRestController {
    /**
     * Retrieves a list of DeliveryMethodDTOs from the database based on specified parameters.
     * @param active        An optional flag indicating whether delivery methods should be active or not.
     * @return              A ResponseEntity containing a list of DeliveryMethodDTOs objects matching the specified criteria.
     */
    ResponseEntity<List<DeliveryMethodDTO>> getAllInstances(Optional<Boolean> active);
}

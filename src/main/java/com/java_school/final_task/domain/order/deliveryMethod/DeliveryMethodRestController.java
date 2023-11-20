package com.java_school.final_task.domain.order.deliveryMethod;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * RestController interface of the {@link DeliveryMethodEntity} entity. Handles the REST methods. Uses
 * {@link DeliveryMethodDTO} as returning object.
 */
public interface DeliveryMethodRestController {
    /**
     * Retrieves a list of {@link DeliveryMethodDTO}s from the database based on specified parameters.
     * @param active        An optional flag indicating whether delivery methods should be active or not.
     * @return              ResponseEntity containing a list of {@link DeliveryMethodDTO}s matching the specified criteria.
     */
    ResponseEntity<List<DeliveryMethodDTO>> getAllInstances(Optional<Boolean> active);
}

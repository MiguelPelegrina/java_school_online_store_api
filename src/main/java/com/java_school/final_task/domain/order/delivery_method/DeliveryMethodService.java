package com.java_school.final_task.domain.order.delivery_method;


import com.java_school.final_task.domain.order.delivery_method.impl.DeliveryMethodRestControllerImpl;

import java.util.List;
import java.util.Optional;

/**
 * Service interface responsible for the interaction between the {@link DeliveryMethodRepository} and the
 * {@link DeliveryMethodRestControllerImpl}. Obtains data from the
 * {@link DeliveryMethodRepository} and returns the object(s) of the entity {@link DeliveryMethodEntity} as
 * {@link DeliveryMethodDTO} to the {@link DeliveryMethodRestControllerImpl}.
 */
public interface DeliveryMethodService {
    /**
     * Retrieves a list of DeliveryMethodDTOs from the database based on specified parameters.
     *
     * @param active An optional flag indicating whether delivery methods should be active or not.
     * @return List of DeliveryMethodDTOs matching the specified criteria.
     */
    List<DeliveryMethodDTO> getAllInstances(Optional<Boolean> active);
}

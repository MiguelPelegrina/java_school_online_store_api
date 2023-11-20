package com.java_school.final_task.domain.order.paymentMethod;

import com.java_school.final_task.domain.order.paymentMethod.impl.PaymentMethodRestControllerImpl;

import java.util.List;
import java.util.Optional;

/**
 * Service interface responsible for the interaction between the {@link PaymentMethodRepository} and the
 * {@link PaymentMethodRestControllerImpl}. Obtains data from the
 * {@link PaymentMethodRepository} and returns the object(s) of the entity {@link PaymentMethodEntity} as
 * {@link PaymentMethodDTO} to the {@link PaymentMethodRestControllerImpl}.
 */
public interface PaymentMethodService {
    /**
     * Retrieves a list of PaymentMethodDTOs from the database based on specified parameters.
     * @param active        An optional flag indicating whether payment methods should be active or not.
     * @return              List of PaymentMethodDTOs matching the specified criteria.
     */
    List<PaymentMethodDTO> getAllInstances(Optional<Boolean> active);
}

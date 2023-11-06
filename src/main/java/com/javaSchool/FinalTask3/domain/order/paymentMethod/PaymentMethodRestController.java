package com.javaSchool.FinalTask3.domain.order.paymentMethod;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * RestController interface of the {@link PaymentMethodEntity} entity. Handles the REST methods. Uses
 * {@link PaymentMethodDTO} as returning object.
 */
public interface PaymentMethodRestController {
    /**
     * Retrieves a list of PaymentMethodDTOs from the database based on specified parameters.
     * @param active        An optional flag indicating whether payment methods should be active or not.
     * @return              A ResponseEntity containing a list of PaymentMethodDTOs objects matching the specified criteria.
     */
    ResponseEntity<List<PaymentMethodDTO>> getAllInstances(Optional<Boolean> active);
}

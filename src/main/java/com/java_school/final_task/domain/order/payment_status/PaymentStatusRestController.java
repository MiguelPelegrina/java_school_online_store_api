package com.java_school.final_task.domain.order.payment_status;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * RestController interface of the {@link PaymentStatusEntity} entity. Handles the REST methods. Uses
 * {@link PaymentStatusDTO} as returning object.
 */
public interface PaymentStatusRestController {
    /**
     * Retrieves a list of PaymentStatusDTOs from the database based on specified parameters.
     * @param active        An optional flag indicating whether payment statuses should be active or not.
     * @return              A ResponseEntity containing a list of PaymentStatusDTOs objects matching the specified criteria.
     */
    ResponseEntity<List<PaymentStatusDTO>> getAllInstances(Optional<Boolean> active);
}

package com.java_school.final_task.domain.order.order_status;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * RestController interface of the {@link OrderStatusEntity} entity. Handles the REST methods. Uses
 * {@link OrderStatusDTO} as returning object.
 */
public interface OrderStatusRestController {
    /**
     * Retrieves a list of OrderStatusDTOs from the database based on specified parameters.
     * @param active        An optional flag indicating whether order statuses should be active or not.
     * @return              A ResponseEntity containing a list of OrderStatusDTOs objects matching the specified criteria.
     */
    ResponseEntity<List<OrderStatusDTO>> getAllInstances(Optional<Boolean> active);
}

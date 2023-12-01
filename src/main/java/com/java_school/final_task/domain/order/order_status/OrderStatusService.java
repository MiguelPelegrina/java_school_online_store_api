package com.java_school.final_task.domain.order.order_status;


import com.java_school.final_task.domain.order.order_status.impl.OrderStatusRestControllerImpl;

import java.util.List;
import java.util.Optional;

/**
 * Service interface responsible for the interaction between the {@link OrderStatusRepository} and the
 * {@link OrderStatusRestControllerImpl}. Obtains data from the
 * {@link OrderStatusRepository} and returns the object(s) of the entity {@link OrderStatusEntity} as
 * {@link OrderStatusDTO} to the {@link OrderStatusRestControllerImpl}.
 */
public interface OrderStatusService {
    /**
     * Retrieves a list of OrderStatusDTOs from the database based on specified parameters.
     * @param active        An optional flag indicating whether order statuses should be active or not.
     * @return              List of OrderStatusDTOs matching the specified criteria.
     */
    List<OrderStatusDTO> getAllInstances(Optional<Boolean> active);
}

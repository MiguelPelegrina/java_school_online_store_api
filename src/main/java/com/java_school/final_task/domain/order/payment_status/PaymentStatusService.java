package com.java_school.final_task.domain.order.payment_status;

import com.java_school.final_task.domain.order.payment_status.impl.PaymentStatusRestControllerImpl;

import java.util.List;
import java.util.Optional;

/**
 * Service interface responsible for the interaction between the {@link PaymentStatusRepository} and the
 * {@link PaymentStatusRestControllerImpl}. Obtains data from the
 * {@link PaymentStatusRepository} and returns the object(s) of the entity {@link PaymentStatusEntity} as
 * {@link PaymentStatusDTO} to the {@link PaymentStatusRestControllerImpl}.
 */
public interface PaymentStatusService {
    /**
     * Retrieves a list of PaymentStatusDTOs from the database based on specified parameters.
     * @param active        An optional flag indicating whether payment statuses should be active or not.
     * @return              List of PaymentStatusDTOs matching the specified criteria.
     */
    List<PaymentStatusDTO> getAllInstances(Optional<Boolean> active);
}

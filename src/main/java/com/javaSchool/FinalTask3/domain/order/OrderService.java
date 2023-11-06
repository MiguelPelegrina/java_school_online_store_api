package com.javaSchool.FinalTask3.domain.order;

import com.javaSchool.FinalTask3.domain.order.dto.OrderDTO;
import com.javaSchool.FinalTask3.domain.order.dto.SaveOrderDTO;
import com.javaSchool.FinalTask3.domain.order.impl.OrderRestControllerImpl;

/**
 * Service interface responsible for the interaction between the {@link OrderRepository} and the
 * {@link OrderRestControllerImpl}. Obtains data from the {@link OrderRepository} and returns
 * the object(s) of the entity {@link OrderEntity} as {@link OrderDTO} to the {@link OrderRestControllerImpl}.
 */
public interface OrderService {
    /**
     * Saves an order in the system based on the provided {@link SaveOrderDTO}.
     * @param saveOrderDTO A {@link SaveOrderDTO} containing order details to be saved.
     * @return An {@link OrderDTO} representing the saved order.
     */
    OrderDTO saveInstance(SaveOrderDTO saveOrderDTO);
}

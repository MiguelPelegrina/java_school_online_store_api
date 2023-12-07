package com.java_school.final_task.domain.order;

import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.dto.OrderRequestDTO;
import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.order.impl.OrderRestControllerImpl;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Service interface responsible for the interaction between the {@link OrderRepository} and the
 * {@link OrderRestControllerImpl}. Obtains data from the {@link OrderRepository} and returns
 * the object(s) of the entity {@link OrderEntity} as {@link OrderDTO} to the {@link OrderRestControllerImpl}.
 */
public interface OrderService {
    /**
     * Retrieves a page of {@link OrderDTO}s from the database based on {@link OrderRequestDTO}.
     *
     * @param orderRequestDTO {@link OrderRequestDTO} that contains all the specified parameters and sorting criteria.
     * @return ResponseEntity containing a Page of {@link OrderDTO}}s based on the specified criteria.
     */
    Page<OrderDTO> getAllInstances(OrderRequestDTO orderRequestDTO);

    /**
     * Calculates the total revenue for orders placed within a specified date range.
     *
     * @param startDate The start date of the period for which to calculate total revenue.
     * @param endDate   The end date of the period for which to calculate total revenue.
     * @return A {@code BigDecimal} representing the total revenue for the specified date range.
     */
    BigDecimal calculateTotalRevenue(LocalDate startDate, LocalDate endDate);

    /**
     * Saves an order in the system based on the provided {@link SaveOrderDTO}.
     *
     * @param saveOrderDTO A {@link SaveOrderDTO} containing order details to be saved.
     * @return An {@link OrderDTO} representing the saved order.
     */
    OrderDTO saveInstance(SaveOrderDTO saveOrderDTO);
}

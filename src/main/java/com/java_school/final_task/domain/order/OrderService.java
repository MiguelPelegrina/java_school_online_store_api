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
     * Calculates the revenue for a specified year or the last 12 months.
     *
     * @param year           The year for which the revenue should be calculated.
     * @param isLast12Months A boolean flag indicating whether to calculate the revenue for the last 12 months from the current date.
     * @return An array of BigDecimal representing the revenue for each month within the specified year or for the last 12 months.
     */
    BigDecimal[] calculateRevenueOfYearByMonths(LocalDate year, boolean isLast12Months);

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

    /**
     * Generates a PDF document for the details of a specific order.
     *
     * @param id The unique identifier of the order.
     * @return A byte array representing the generated PDF document.
     */
    byte[] generateOrderPDF(Integer id);
}

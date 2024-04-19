package com.java_school.final_task.domain.order;

import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.dto.OrderRequestDTO;
import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * RestController interface of the {@link OrderEntity} entity. Handles the REST methods. Uses {@link OrderDTO} as returning object.
 */
public interface OrderRestController {
    /**
     * Retrieves a page of {@link OrderDTO}s from the database based on specified parameters and sorting criteria.
     *
     * @param orderRequestDTO {@link OrderRequestDTO} that contains all the specified parameters and sorting criteria.
     * @return ResponseEntity containing a Page of {@link OrderDTO}}s based on the specified criteria.
     */
    ResponseEntity<Page<OrderDTO>> getAllInstances(OrderRequestDTO orderRequestDTO);

    /**
     * Endpoint for calculating revenue within a specified date range.
     *
     * @param startDate The start date of the period for which to calculate revenue.
     * @param endDate   The end date of the period for which to calculate revenue.
     * @return {@code ResponseEntity} containing a {@code BigDecimal} representing the calculated revenue.
     */
    ResponseEntity<BigDecimal> calculateTotalRevenue(LocalDate startDate, LocalDate endDate);

    /**
     * Calculates the revenue of a year from January to December based on the provided date.
     *
     * @param date The date to calculate the revenue for, formatted as "dd-MM-yyyy".
     * @return A ResponseEntity containing an array of BigDecimal representing the revenue for each month of the year.
     */
    ResponseEntity<BigDecimal[]> calculateRevenuesOfYearByMonths(LocalDate date);

    /**
     * Calculates the revenue of the last 12 months.
     *
     * @param date The date to calculate the revenue from, formatted as "dd-MM-yyyy".
     * @return A ResponseEntity containing an array of BigDecimal representing the revenue for each of the last 12 months.
     */
    ResponseEntity<BigDecimal[]> calculateRevenuesOfLast12Months(LocalDate date);

    /**
     * Submits a POST request with a {@link SaveOrderDTO} to create an order instance in the database.
     *
     * @param saveOrderDTO Instance to create.
     * @return Returns a ResponseEntity with the {@link OrderDTO} and the status of the POST request.
     * If successful, the code is 200 created successfully, 204 otherwise.
     */
    ResponseEntity<OrderDTO> saveInstance(SaveOrderDTO saveOrderDTO);


}

package com.javaSchool.FinalTask3.domain.order;

import com.javaSchool.FinalTask3.domain.order.dto.OrderDTO;
import com.javaSchool.FinalTask3.domain.order.dto.SaveOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

/**
 * RestController interface of the {@link OrderEntity} entity. Handles the REST methods. Uses {@link OrderDTO} as returning object.
 */
public interface OrderRestController {
    /**
     * Retrieves a page of {@link OrderDTO}s from the database based on specified parameters and sorting criteria.
     * @param date The date of the order.
     * @param deliveryMethod  The delivery method of the order to search for.
     * @param orderStatus     The order status of the order to search for.
     * @param paymentMethod   The payment method of the order to search for.
     * @param paymentStatus   The payment status of the order to search for.
     * @param name            The part of the name of the user to search for.
     * @param sortType        The sorting direction.
     * @param sortProperty    The property by which to sort the results.
     * @param page            The page number to retrieve.
     * @param size            The number of items per page.
     * @return                ResponseEntity containing a Page of {@link OrderDTO}}s based on the specified criteria.
     */
    ResponseEntity<Page<OrderDTO>> getAllInstances(LocalDate date, String deliveryMethod, String orderStatus,
            String paymentMethod, String paymentStatus, String name, String sortType, String sortProperty, Integer page,
            Integer size
    );

    /**
     * Submits a POST request with a {@link SaveOrderDTO} to create an order instance in the database.
     * @param saveOrderDTO Instance to create.
     * @return Returns a ResponseEntity with the {@link OrderDTO} and the status of the POST request.
     * If successful, the code is 200 created successfully, 204 otherwise.
     */
    ResponseEntity<OrderDTO> saveInstance( SaveOrderDTO saveOrderDTO);
}

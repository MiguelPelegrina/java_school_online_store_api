package com.javaSchool.FinalTask3.domain.order;

import com.javaSchool.FinalTask3.domain.order.dto.SaveOrderDTO;
import org.springframework.http.ResponseEntity;

/**
 * RestController interface of the {@link OrderEntity} entity. Handles the REST methods. Uses {@link OrderDTO} as returning object.
 */
public interface OrderRestController {
    /**
     * Submits a POST request with a {@link SaveOrderDTO} to create an order instance in the database.
     * @param saveOrderDTO Instance to create.
     * @return Returns a ResponseEntity with the {@link OrderDTO} and the status of the POST request.
     * If successful, the code is 200 created successfully, 204 otherwise.
     */
    ResponseEntity<com.javaSchool.FinalTask3.domain.order.OrderDTO> saveInstance( SaveOrderDTO saveOrderDTO);
}

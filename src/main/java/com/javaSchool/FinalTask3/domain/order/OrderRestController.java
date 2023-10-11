package com.javaSchool.FinalTask3.domain.order;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link OrderEntity} entity. Handles the REST methods. Uses
 * {@link OrderDTO} as returning object.
 */
@RequestMapping(path = "orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class OrderRestController extends AbstractRestControllerWithUpdate<OrderEntity, OrderDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link OrderService} of the {@link OrderEntity} entity.
     */
    public OrderRestController(AbstractServiceWithUpdate<OrderEntity, OrderDTO, Integer> service) {
        super(service);
    }
}

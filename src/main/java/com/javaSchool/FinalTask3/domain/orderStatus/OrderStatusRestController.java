package com.javaSchool.FinalTask3.domain.orderStatus;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link OrderStatusEntity} entity. Handles the REST methods. Uses
 * {@link OrderStatusDTO} as returning object.
 */
@RequestMapping(path = "/orderstatuses")
@RestController
public class OrderStatusRestController extends AbstractRestControllerWithUpdate<OrderStatusEntity, OrderStatusDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link OrderStatusService} of the {@link OrderStatusEntity} entity.
     */
    public OrderStatusRestController(AbstractServiceWithUpdate<OrderStatusEntity, OrderStatusDTO, String> service) {
        super(service);
    }
}

package com.javaSchool.FinalTask3.domain.orderStatus;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link OrderStatusEntity} entity. Handles the REST methods. Uses
 * {@link OrderStatusDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/orderstatuses")
@RestController
public class OrderStatusRestController extends AbstractRestController<OrderStatusEntity, OrderStatusDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link OrderStatusService} of the {@link OrderStatusEntity} entity.
     */
    public OrderStatusRestController(AbstractService<OrderStatusEntity, OrderStatusDTO, String> service) {
        super(service);
    }
}

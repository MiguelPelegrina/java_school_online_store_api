package com.javaSchool.FinalTask3.domain.orderStatus;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
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
public class OrderStatusRestControllerImpl extends AbstractRestControllerImpl<OrderStatusEntity, OrderStatusDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link OrderStatusServiceImpl} of the {@link OrderStatusEntity} entity.
     */
    public OrderStatusRestControllerImpl(AbstractServiceImpl<OrderStatusEntity, OrderStatusDTO, String> service) {
        super(service);
    }
}

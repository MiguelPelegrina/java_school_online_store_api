package com.javaSchool.FinalTask3.domain.order.impl;

import com.javaSchool.FinalTask3.domain.order.OrderDTO;
import com.javaSchool.FinalTask3.domain.order.OrderEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link OrderEntity} entity. Handles the REST methods. Uses
 * {@link OrderDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "orders")
@RestController
public class OrderRestControllerImpl extends AbstractRestControllerImpl<OrderEntity, OrderDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link OrderServiceImpl} of the {@link OrderEntity} entity.
     */
    public OrderRestControllerImpl(AbstractServiceImpl<OrderEntity, OrderDTO, Integer> service) {
        super(service);
    }
}

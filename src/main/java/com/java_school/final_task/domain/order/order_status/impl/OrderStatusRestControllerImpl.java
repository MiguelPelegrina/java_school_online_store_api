package com.java_school.final_task.domain.order.order_status.impl;

import com.java_school.final_task.domain.order.order_status.OrderStatusDTO;
import com.java_school.final_task.domain.order.order_status.OrderStatusEntity;
import com.java_school.final_task.domain.order.order_status.OrderStatusRepository;
import com.java_school.final_task.domain.order.order_status.OrderStatusRestController;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * RestController of the {@link OrderStatusEntity} entity. Handles the REST methods. Uses
 * {@link OrderStatusDTO} as returning object.
 */
@RequestMapping(path = "/order_statuses")
@RestController
public class OrderStatusRestControllerImpl
        extends AbstractRestControllerImpl<OrderStatusServiceImpl, OrderStatusRepository, OrderStatusEntity, OrderStatusDTO, String>
        implements OrderStatusRestController {
    /**
     * All arguments constructor.
     * @param service {@link OrderStatusServiceImpl} of the {@link OrderStatusEntity} entity.
     */
    public OrderStatusRestControllerImpl(OrderStatusServiceImpl service) {
        super(service);
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<List<OrderStatusDTO>> getAllInstances(
            @RequestParam("active") Optional<Boolean> active){
        return ResponseEntity.ok(this.service.getAllInstances(active));
    }
}

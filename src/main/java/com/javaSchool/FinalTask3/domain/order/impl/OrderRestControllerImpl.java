package com.javaSchool.FinalTask3.domain.order.impl;

import com.javaSchool.FinalTask3.domain.order.OrderRequest;
import com.javaSchool.FinalTask3.domain.order.OrderRestController;
import com.javaSchool.FinalTask3.domain.order.dto.OrderDTO;
import com.javaSchool.FinalTask3.domain.order.dto.SaveOrderDTO;
import com.javaSchool.FinalTask3.domain.order.OrderEntity;
import com.javaSchool.FinalTask3.domain.order.OrderRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * RestController of the {@link OrderEntity} entity. Handles the REST methods. Uses {@link OrderDTO} as returning object.
 */
@RequestMapping(path = "orders")
@RestController
public class OrderRestControllerImpl
        extends AbstractRestControllerImpl<OrderServiceImpl, OrderRepository, OrderEntity, OrderDTO, Integer>
        implements OrderRestController {
    /**
     * All arguments constructor.
     * @param service {@link OrderServiceImpl} of the {@link OrderEntity} entity.
     */
    public OrderRestControllerImpl(OrderServiceImpl service) {
        super(service);
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<Page<OrderDTO>> getAllInstances(OrderRequest orderRequest) {
        PageRequest pageRequest = PageRequest.of(
                orderRequest.getPage(),
                orderRequest.getSize(),
                Sort.Direction.valueOf(orderRequest.getSortType()),
                orderRequest.getSortProperty());

        return ResponseEntity.ok(this.service.getAllInstances(
                orderRequest.getDate(),
                orderRequest.getDeliveryMethod(),
                orderRequest.getOrderStatus(), orderRequest.getPaymentMethod(),
                orderRequest.getPaymentStatus(),
                orderRequest.getName(), pageRequest
        ));
    }

    // TODO This might not be the right approach, but I can't figure out how to create an JSON object with a circular
    //  reference on the frontend so that I can send a complete order.
    @PostMapping("/withBooks")
    @Operation(summary = "Saves an instance of an entity into the repository or updates it, if already exists.")
    @Override
    public ResponseEntity<OrderDTO> saveInstance(@RequestBody SaveOrderDTO saveOrderDTO){
        OrderDTO savedOrder = service.saveInstance(saveOrderDTO);

        if (savedOrder == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(savedOrder);
        }
    }
}

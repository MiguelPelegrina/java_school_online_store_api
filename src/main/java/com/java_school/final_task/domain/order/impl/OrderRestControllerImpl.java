package com.java_school.final_task.domain.order.impl;

import com.java_school.final_task.domain.order.OrderRequest;
import com.java_school.final_task.domain.order.OrderRestController;
import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.order.OrderEntity;
import com.java_school.final_task.domain.order.OrderRepository;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        return ResponseEntity.ok(this.service.getAllInstances(orderRequest));
    }

    @GetMapping("/revenue")
    @Override
    public ResponseEntity<BigDecimal> calculateTotalRevenue(
            @RequestParam("start") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
            @RequestParam("end") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate){
        return ResponseEntity.ok(this.service.calculateTotalRevenue(startDate, endDate));
    }

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

package com.java_school.final_task.domain.order.order_status.impl;

import com.java_school.final_task.domain.order.order_status.OrderStatusDTO;
import com.java_school.final_task.domain.order.order_status.OrderStatusEntity;
import com.java_school.final_task.domain.order.order_status.OrderStatusRepository;
import com.java_school.final_task.domain.order.order_status.OrderStatusRestController;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
     *
     * @param service {@link OrderStatusServiceImpl} of the {@link OrderStatusEntity} entity.
     */
    public OrderStatusRestControllerImpl(OrderStatusServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved a list of order statuses",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/search")
    @Operation(summary = "Retrieves a list of order statuses")
    @Override
    public ResponseEntity<List<OrderStatusDTO>> getAllInstances(
            @Parameter(description = "Active state of the order status", example = "true")
            @RequestParam("active") Optional<Boolean> active) {
        return ResponseEntity.ok(this.service.getAllInstances(active));
    }
}

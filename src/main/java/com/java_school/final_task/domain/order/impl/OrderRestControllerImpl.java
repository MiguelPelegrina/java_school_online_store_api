package com.java_school.final_task.domain.order.impl;

import com.java_school.final_task.domain.order.OrderEntity;
import com.java_school.final_task.domain.order.OrderRepository;
import com.java_school.final_task.domain.order.OrderRestController;
import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.dto.OrderRequestDTO;
import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     *
     * @param service {@link OrderServiceImpl} of the {@link OrderEntity} entity.
     */
    public OrderRestControllerImpl(OrderServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved a list of orders",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/search")
    @Operation(summary = "Retrieves a list of orders")
    @Override
    public ResponseEntity<Page<OrderDTO>> getAllInstances(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Order request", required = true,
                    content = @Content(schema = @Schema(implementation = OrderRequestDTO.class)))
            OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.ok(this.service.getAllInstances(orderRequestDTO));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculate the total revenue",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/revenue")
    @Operation(summary = "Calculates the revenue generated between two dates")
    @Override
    public ResponseEntity<BigDecimal> calculateTotalRevenue(
            @Parameter(description = "Start date", example = "01-12-2023")
            @RequestParam("start") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
            @Parameter(description = "End date", example = "12-12-2023")
            @RequestParam("end") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
        return ResponseEntity.ok(this.service.calculateTotalRevenue(startDate, endDate));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculate the revenue of a year from january to december",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/revenueOfYearByMonths")
    @Override
    public ResponseEntity<BigDecimal[]> calculateRevenuesOfYearByMonths(
            @RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return ResponseEntity.ok(this.service.calculateRevenueOfYearByMonths(date, false));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculate the revenue of the last 12 months",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/revenueOfLast12Months")
    @Override
    public ResponseEntity<BigDecimal[]> calculateRevenuesOfLast12Months(
            @RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return ResponseEntity.ok(this.service.calculateRevenueOfYearByMonths(date, true));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved the order",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @PostMapping("/withBooks")
    @Operation(summary = "Saves a order or updates it, if already exists")
    @Override
    public ResponseEntity<OrderDTO> saveInstance(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Save order DTO", required = true,
                    content = @Content(schema = @Schema(implementation = SaveOrderDTO.class)))
            @RequestBody SaveOrderDTO saveOrderDTO) {
        OrderDTO savedOrder = service.saveInstance(saveOrderDTO);

        if (savedOrder == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(savedOrder);
        }
    }

    @GetMapping("/generateOrderPDF/{id}")
    @Override
    public ResponseEntity<byte[]> generateOrderPDF(@PathVariable Integer id) {
        return ResponseEntity.ok(this.service.generateOrderPDF(id));
    }
}

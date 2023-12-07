package com.java_school.final_task.domain.order.payment_status.impl;

import com.java_school.final_task.domain.order.payment_status.PaymentStatusDTO;
import com.java_school.final_task.domain.order.payment_status.PaymentStatusEntity;
import com.java_school.final_task.domain.order.payment_status.PaymentStatusRepository;
import com.java_school.final_task.domain.order.payment_status.PaymentStatusRestController;
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
 * RestController of the {@link PaymentStatusEntity} entity. Handles the REST methods. Uses
 * {@link PaymentStatusDTO} as returning object.
 */
@RequestMapping(path = "payment_statuses")
@RestController
public class PaymentStatusRestControllerImpl
        extends AbstractRestControllerImpl<PaymentStatusServiceImpl, PaymentStatusRepository, PaymentStatusEntity, PaymentStatusDTO, String>
        implements PaymentStatusRestController {
    /**
     * All arguments constructor.
     *
     * @param service {@link PaymentStatusServiceImpl} of the {@link PaymentStatusEntity} entity.
     */
    public PaymentStatusRestControllerImpl(PaymentStatusServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved a list of payment statuses",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/search")
    @Operation(summary = "Retrieves a list of payment statuses")
    @Override
    public ResponseEntity<List<PaymentStatusDTO>> getAllInstances(
            @Parameter(description = "Active state of the payment method", example = "true")
            @RequestParam("active") Optional<Boolean> active) {
        return ResponseEntity.ok(this.service.getAllInstances(active));
    }
}

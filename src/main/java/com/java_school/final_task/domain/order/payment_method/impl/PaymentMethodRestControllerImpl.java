package com.java_school.final_task.domain.order.payment_method.impl;

import com.java_school.final_task.domain.order.payment_method.PaymentMethodDTO;
import com.java_school.final_task.domain.order.payment_method.PaymentMethodEntity;
import com.java_school.final_task.domain.order.payment_method.PaymentMethodRepository;
import com.java_school.final_task.domain.order.payment_method.PaymentMethodRestController;
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
 * RestController of the {@link PaymentMethodEntity} entity. Handles the REST methods. Uses
 * {@link PaymentMethodDTO} as returning object.
 */
@RequestMapping(path = "payment_methods")
@RestController
public class PaymentMethodRestControllerImpl
        extends AbstractRestControllerImpl<PaymentMethodServiceImpl, PaymentMethodRepository, PaymentMethodEntity, PaymentMethodDTO, String>
        implements PaymentMethodRestController {
    /**
     * All arguments constructor.
     *
     * @param service {@link PaymentMethodServiceImpl} of the {@link PaymentMethodEntity} entity.
     */
    public PaymentMethodRestControllerImpl(PaymentMethodServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved a list of payment methods",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/search")
    @Operation(summary = "Retrieves a list of payment methods")
    @Override
    public ResponseEntity<List<PaymentMethodDTO>> getAllInstances(
            @Parameter(description = "Active state of the payment method", example = "true")
            @RequestParam("active") Optional<Boolean> active) {
        return ResponseEntity.ok(this.service.getAllInstances(active));
    }
}

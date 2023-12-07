package com.java_school.final_task.domain.order.delivery_method.impl;

import com.java_school.final_task.domain.order.delivery_method.DeliveryMethodDTO;
import com.java_school.final_task.domain.order.delivery_method.DeliveryMethodEntity;
import com.java_school.final_task.domain.order.delivery_method.DeliveryMethodRepository;
import com.java_school.final_task.domain.order.delivery_method.DeliveryMethodRestController;
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
 * RestController of the {@link DeliveryMethodEntity} entity. Handles the REST methods. Uses {@link DeliveryMethodDTO}
 * as returning object.
 */
@RequestMapping(path = "delivery_methods")
@RestController
public class DeliveryMethodRestControllerImpl
        extends AbstractRestControllerImpl<DeliveryMethodServiceImpl, DeliveryMethodRepository, DeliveryMethodEntity, DeliveryMethodDTO, String>
        implements DeliveryMethodRestController {
    /**
     * All arguments constructor.
     *
     * @param service {@link DeliveryMethodServiceImpl} of the {@link DeliveryMethodEntity} entity.
     */
    public DeliveryMethodRestControllerImpl(DeliveryMethodServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved a list of delivery methods",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/search")
    @Operation(summary = "Retrieves a list of delivery methods")
    @Override
    public ResponseEntity<List<DeliveryMethodDTO>> getAllInstances(
            @Parameter(description = "Active state of the delivery method", example = "true")
            @RequestParam("active") Optional<Boolean> active) {
        return ResponseEntity.ok(this.service.getAllInstances(active));
    }
}

package com.java_school.final_task.domain.user.user_address.postal_code.impl;

import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeRepository;
import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeRestController;
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
 * RestController of the {@link PostalCodeEntity} entity. Handles the REST methods. Uses
 * {@link PostalCodeDTO} as returning object.
 */
@RequestMapping(path = "postal_codes")
@RestController
public class PostalCodeRestControllerImpl
        extends AbstractRestControllerImpl<PostalCodeServiceImpl, PostalCodeRepository, PostalCodeEntity, PostalCodeDTO, String>
        implements PostalCodeRestController {
    /**
     * All arguments constructor.
     *
     * @param service {@link PostalCodeServiceImpl} of the {@link PostalCodeEntity} entity.
     */
    public PostalCodeRestControllerImpl(PostalCodeServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved a list of postal codes",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/search")
    @Operation(summary = "Retrieves a list of postal codes")
    @Override
    public ResponseEntity<List<PostalCodeDTO>> getAllInstances(
            @Parameter(description = "Code of the postal code", example = "18014")
            @RequestParam(name = "city_name", defaultValue = "") String cityName,
            @Parameter(description = "Active state of the postal code", example = "true")
            @RequestParam("active") Optional<Boolean> active
    ) {
        return ResponseEntity.ok(this.service.getAllInstances(cityName, active));
    }
}

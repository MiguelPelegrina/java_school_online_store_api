package com.java_school.final_task.domain.user.user_address.postal_code.city.country.impl;

import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryRepository;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryRestController;
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
 * RestController of the {@link CountryEntity} entity. Handles the REST methods. Uses {@link CountryDTO} as returning
 * object.
 */
@RequestMapping(path = "countries")
@RestController
public class CountryRestControllerImpl
        extends AbstractRestControllerImpl<CountryServiceImpl, CountryRepository, CountryEntity, CountryDTO, String>
        implements CountryRestController {
    /**
     * All arguments constructor.
     *
     * @param service {@link CountryServiceImpl} of the {@link CountryEntity} entity.
     */
    public CountryRestControllerImpl(CountryServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved a list of countries",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/search")
    @Operation(summary = "Retrieves a list of countries")
    @Override
    public ResponseEntity<List<CountryDTO>> getAllInstances(
            @Parameter(description = "active status of the country", example = "true")
            @RequestParam("active") Optional<Boolean> active,
            @Parameter(description = "name of the country", example = "Spain")
            @RequestParam(value = "name", defaultValue = "") String name) {
        return ResponseEntity.ok(this.service.getAllInstances(active, name));
    }
}

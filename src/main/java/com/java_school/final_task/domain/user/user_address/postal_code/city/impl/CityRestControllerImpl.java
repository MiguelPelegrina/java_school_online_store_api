package com.java_school.final_task.domain.user.user_address.postal_code.city.impl;

import com.java_school.final_task.domain.user.user_address.postal_code.city.CityDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.city.CityEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.city.CityRepository;
import com.java_school.final_task.domain.user.user_address.postal_code.city.CityRestController;
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
 * RestController of the {@link CityEntity} entity. Handles the REST methods. Uses
 * {@link CityDTO} as returning object.
 */
@RequestMapping(path = "cities")
@RestController
public class CityRestControllerImpl
        extends AbstractRestControllerImpl<CityServiceImpl, CityRepository, CityEntity, CityDTO, String>
        implements CityRestController {
    /**
     * All arguments constructor.
     *
     * @param service {@link CityServiceImpl} of the {@link CityEntity} entity.
     */
    public CityRestControllerImpl(CityServiceImpl service) {
        super(service);
    }

    /**
     * Retrieves a list of CityDTO objects from the database based on specified parameters.
     *
     * @param countryName The name of the country associated with the cities (case-insensitive). Default is an empty string.
     * @param active      An optional flag indicating whether cities should be active or not.
     * @return ResponseEntity containing a list of CityDTO objects matching the specified criteria.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved a list of cities",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/search")
    @Operation(summary = "Retrieves a list of cities")
    @Override
    public ResponseEntity<List<CityDTO>> getAllInstances(
            @Parameter(description = "Name of the city", example = "Granada")
            @RequestParam(name = "country_name", defaultValue = "") String countryName,
            @Parameter(description = "Active status of the city", example = "True")
            @RequestParam("active") Optional<Boolean> active
    ) {
        return ResponseEntity.ok(this.service.getAllInstances(countryName, active));
    }
}

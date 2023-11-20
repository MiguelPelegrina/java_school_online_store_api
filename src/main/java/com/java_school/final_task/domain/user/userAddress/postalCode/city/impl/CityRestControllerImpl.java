package com.java_school.final_task.domain.user.userAddress.postalCode.city.impl;

import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityRepository;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityRestController;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param service {@link CityServiceImpl} of the {@link CityEntity} entity.
     */
    public CityRestControllerImpl(CityServiceImpl service) {
        super(service);
    }

    /**
     * Retrieves a list of CityDTO objects from the database based on specified parameters.
     *
     * @param countryName   The name of the country associated with the cities (case-insensitive). Default is an empty string.
     * @param active        An optional flag indicating whether cities should be active or not.
     * @return              ResponseEntity containing a list of CityDTO objects matching the specified criteria.
     */
    @GetMapping("/search")
    @Override
    public ResponseEntity<List<CityDTO>> getAllInstances(
            @RequestParam(name = "country_name", defaultValue = "") String countryName,
            @RequestParam("active") Optional<Boolean> active
    ){
        return ResponseEntity.ok(this.service.getAllInstances(countryName, active));
    }
}

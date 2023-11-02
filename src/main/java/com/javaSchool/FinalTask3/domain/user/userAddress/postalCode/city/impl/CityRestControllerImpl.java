package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.CityDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.CityEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.CityRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
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
        extends AbstractRestControllerImpl<CityServiceImpl, CityRepository, CityEntity, CityDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link CityServiceImpl} of the {@link CityEntity} entity.
     */
    public CityRestControllerImpl(CityServiceImpl service) {
        super(service);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CityDTO>> getAllInstances(
            @RequestParam(name = "country_name", defaultValue = "") String countryName,
            @RequestParam("active") Optional<Boolean> active
    ){
        return ResponseEntity.ok(this.service.getAllInstances(countryName, active));
    }
}

package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * RestController of the {@link CountryEntity} entity. Handles the REST methods. Uses {@link CountryDTO} as returning
 * object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "countries")
@RestController
public class CountryRestControllerImpl extends AbstractRestControllerImpl<CountryEntity, CountryDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link CountryServiceImpl} of the {@link CountryEntity} entity.
     */
    public CountryRestControllerImpl(AbstractServiceImpl<CountryEntity, CountryDTO, String> service) {
        super(service);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CountryDTO>> getAllInstances(@RequestParam("active") Optional<Boolean> active){
        CountryServiceImpl countryService = (CountryServiceImpl) this.service;

        return ResponseEntity.ok(countryService.getAllInstances(active));
    }
}

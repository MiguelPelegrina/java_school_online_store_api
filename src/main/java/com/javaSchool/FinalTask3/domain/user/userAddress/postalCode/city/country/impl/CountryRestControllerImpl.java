package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * RestController of the {@link CountryEntity} entity. Handles the REST methods. Uses {@link CountryDTO} as returning
 * object.
 */
@RequestMapping(path = "countries")
@RestController
public class CountryRestControllerImpl
        extends AbstractRestControllerImpl<CountryServiceImpl, CountryRepository, CountryEntity, CountryDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link CountryServiceImpl} of the {@link CountryEntity} entity.
     */
    public CountryRestControllerImpl(CountryServiceImpl service) {
        super(service);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CountryDTO>> getAllInstances(
            @RequestParam("active") Optional<Boolean> active,
            @RequestParam(value = "country_name", defaultValue = "") String countryName){
        return ResponseEntity.ok(this.service.getAllInstances(active, countryName));
    }
}

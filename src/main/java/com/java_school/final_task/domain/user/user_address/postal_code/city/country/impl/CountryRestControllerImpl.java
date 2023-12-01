package com.java_school.final_task.domain.user.user_address.postal_code.city.country.impl;

import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryRepository;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryRestController;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
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

    @GetMapping("/search")
    @Override
    public ResponseEntity<List<CountryDTO>> getAllInstances(
            @RequestParam("active") Optional<Boolean> active,
            @RequestParam(value = "name", defaultValue = "") String name) {
        return ResponseEntity.ok(this.service.getAllInstances(active, name));
    }
}

package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * RestController of the {@link PostalCodeEntity} entity. Handles the REST methods. Uses
 * {@link PostalCodeDTO} as returning object.
 */
@RequestMapping(path = "postal_codes")
@RestController
public class PostalCodeRestControllerImpl
        extends AbstractRestControllerImpl<PostalCodeServiceImpl, PostalCodeRepository, PostalCodeEntity, PostalCodeDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PostalCodeServiceImpl} of the {@link PostalCodeEntity} entity.
     */
    public PostalCodeRestControllerImpl(PostalCodeServiceImpl service) {
        super(service);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostalCodeDTO>> getAllInstances(
            @RequestParam(name = "city_name", defaultValue = "") String cityName,
            @RequestParam("active") Optional<Boolean> active
            ){
        return ResponseEntity.ok(this.service.getAllInstances(cityName, active));
    }
}

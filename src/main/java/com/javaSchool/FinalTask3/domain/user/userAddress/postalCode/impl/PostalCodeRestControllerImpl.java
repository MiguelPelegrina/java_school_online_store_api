package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * RestController of the {@link PostalCodeEntity} entity. Handles the REST methods. Uses
 * {@link PostalCodeDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "postal_codes")
@RestController
public class PostalCodeRestControllerImpl extends AbstractRestControllerImpl<PostalCodeEntity, PostalCodeDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PostalCodeServiceImpl} of the {@link PostalCodeEntity} entity.
     */
    public PostalCodeRestControllerImpl(AbstractServiceImpl<PostalCodeEntity, PostalCodeDTO, String> service) {
        super(service);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostalCodeDTO>> getAllInstances(
            @RequestParam(name = "city_name") String cityName,
            @RequestParam("active") Optional<Boolean> active
            ){
        PostalCodeServiceImpl postalCodeService = (PostalCodeServiceImpl) this.service;

        return ResponseEntity.ok(postalCodeService.getAllInstances(cityName, active));
    }
}

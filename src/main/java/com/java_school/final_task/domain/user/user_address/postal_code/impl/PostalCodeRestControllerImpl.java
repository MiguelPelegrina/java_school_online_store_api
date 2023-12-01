package com.java_school.final_task.domain.user.user_address.postal_code.impl;

import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeRepository;
import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeRestController;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
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

    @GetMapping("/search")
    @Override
    public ResponseEntity<List<PostalCodeDTO>> getAllInstances(
            @RequestParam(name = "city_name", defaultValue = "") String cityName,
            @RequestParam("active") Optional<Boolean> active
    ) {
        return ResponseEntity.ok(this.service.getAllInstances(cityName, active));
    }
}

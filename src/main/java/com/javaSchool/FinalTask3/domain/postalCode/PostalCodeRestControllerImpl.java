package com.javaSchool.FinalTask3.domain.postalCode;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link PostalCodeEntity} entity. Handles the REST methods. Uses
 * {@link PostalCodeDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "postalcodes")
@RestController
public class PostalCodeRestControllerImpl extends AbstractRestControllerImpl<PostalCodeEntity, PostalCodeDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PostalCodeServiceImpl} of the {@link PostalCodeEntity} entity.
     */
    public PostalCodeRestControllerImpl(AbstractServiceImpl<PostalCodeEntity, PostalCodeDTO, String> service) {
        super(service);
    }
}

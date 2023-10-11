package com.javaSchool.FinalTask3.domain.postalCode;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link PostalCodeEntity} entity. Handles the REST methods. Uses
 * {@link PostalCodeDTO} as returning object.
 */
@RequestMapping(path = "postalcodes")
@RestController
public class PostalCodeController extends AbstractRestControllerWithUpdate<PostalCodeEntity, PostalCodeDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PostalCodeService} of the {@link PostalCodeEntity} entity.
     */
    public PostalCodeController(AbstractServiceWithUpdate<PostalCodeEntity, PostalCodeDTO, String> service) {
        super(service);
    }
}

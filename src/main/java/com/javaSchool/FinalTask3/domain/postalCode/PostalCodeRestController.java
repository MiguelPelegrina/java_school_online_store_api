package com.javaSchool.FinalTask3.domain.postalCode;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link PostalCodeEntity} entity. Handles the REST methods. Uses
 * {@link PostalCodeDTO} as returning object.
 */
@RequestMapping(path = "postalcodes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PostalCodeRestController extends AbstractRestController<PostalCodeEntity, PostalCodeDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PostalCodeService} of the {@link PostalCodeEntity} entity.
     */
    public PostalCodeRestController(AbstractService<PostalCodeEntity, PostalCodeDTO, String> service) {
        super(service);
    }
}

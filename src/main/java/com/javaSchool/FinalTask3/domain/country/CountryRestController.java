package com.javaSchool.FinalTask3.domain.country;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link CountryEntity} entity. Handles the REST methods. Uses {@link CountryDTO} as returning
 * object.
 */
@RequestMapping(path = "countries", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CountryRestController extends AbstractRestControllerWithUpdate<CountryEntity, CountryDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link CountryService} of the {@link CountryEntity} entity.
     */
    public CountryRestController(AbstractServiceWithUpdate<CountryEntity, CountryDTO, String> service) {
        super(service);
    }
}

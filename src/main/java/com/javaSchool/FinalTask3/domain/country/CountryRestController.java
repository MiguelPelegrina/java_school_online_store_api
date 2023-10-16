package com.javaSchool.FinalTask3.domain.country;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link CountryEntity} entity. Handles the REST methods. Uses {@link CountryDTO} as returning
 * object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "countries")
@RestController
public class CountryRestController extends AbstractRestController<CountryEntity, CountryDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link CountryService} of the {@link CountryEntity} entity.
     */
    public CountryRestController(AbstractService<CountryEntity, CountryDTO, String> service) {
        super(service);
    }
}

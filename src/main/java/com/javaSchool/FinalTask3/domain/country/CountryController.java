package com.javaSchool.FinalTask3.domain.country;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractControllerWithUpdate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO What information do I specify in the HttpHeaders?
@RequestMapping(path = "countries")
@RestController
public class CountryController extends AbstractControllerWithUpdate<CountryEntity, CountryDTO, String> {
    public CountryController(AbstractServiceWithUpdate<CountryEntity, CountryDTO, String> service) {
        super(service);
    }
}

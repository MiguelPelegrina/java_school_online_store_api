package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
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
public class CountryRestControllerImpl extends AbstractRestControllerImpl<CountryEntity, CountryDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link CountryServiceImpl} of the {@link CountryEntity} entity.
     */
    public CountryRestControllerImpl(AbstractServiceImpl<CountryEntity, CountryDTO, String> service) {
        super(service);
    }
}

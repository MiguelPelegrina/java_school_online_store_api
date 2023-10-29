package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link CityEntity} entity. Handles the REST methods. Uses
 * {@link CityDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "cities")
@RestController
public class CityRestControllerImpl extends AbstractRestControllerImpl<CityEntity, CityDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link CityServiceImpl} of the {@link CityEntity} entity.
     */
    public CityRestControllerImpl(AbstractServiceImpl<CityEntity, CityDTO, String> service) {
        super(service);
    }
}

package com.javaSchool.FinalTask3.domain.city;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.http.MediaType;
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
public class CityRestController extends AbstractRestController<CityEntity, CityDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link CityService} of the {@link CityEntity} entity.
     */
    public CityRestController(AbstractService<CityEntity, CityDTO, String> service) {
        super(service);
    }
}

package com.javaSchool.FinalTask3.domain.city;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link CityEntity} entity. Handles the REST methods. Uses
 * {@link CityDTO} as returning object.
 */
@RequestMapping(path = "cities", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CityRestController extends AbstractRestControllerWithUpdate<CityEntity, CityDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link CityService} of the {@link CityEntity} entity.
     */
    public CityRestController(AbstractServiceWithUpdate<CityEntity, CityDTO, String> service) {
        super(service);
    }
}

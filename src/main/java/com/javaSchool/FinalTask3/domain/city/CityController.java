package com.javaSchool.FinalTask3.domain.city;

import com.javaSchool.FinalTask3.utils.AbstractControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "cities")
@RestController
public class CityController extends AbstractControllerWithUpdate<CityEntity, CityDTO, String> {
    public CityController(AbstractServiceWithUpdate<CityEntity, CityDTO, String> service) {
        super(service);
    }
}

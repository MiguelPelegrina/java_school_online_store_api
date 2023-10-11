package com.javaSchool.FinalTask3.domain.deliveryMethod;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link DeliveryMethodEntity} entity. Handles the REST methods. Uses
 * {@link DeliveryMethodDTO} as returning object.
 */
@RequestMapping(path = "deliverymethods", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class DeliveryMethodRestController extends AbstractRestControllerWithUpdate<DeliveryMethodEntity, DeliveryMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link DeliveryMethodService} of the {@link DeliveryMethodEntity} entity.
     */
    public DeliveryMethodRestController(AbstractServiceWithUpdate<DeliveryMethodEntity, DeliveryMethodDTO, String> service) {
        super(service);
    }
}

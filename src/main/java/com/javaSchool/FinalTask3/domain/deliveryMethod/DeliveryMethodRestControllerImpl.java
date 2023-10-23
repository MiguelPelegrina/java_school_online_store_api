package com.javaSchool.FinalTask3.domain.deliveryMethod;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link DeliveryMethodEntity} entity. Handles the REST methods. Uses
 * {@link DeliveryMethodDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "deliverymethods")
@RestController
public class DeliveryMethodRestControllerImpl extends AbstractRestControllerImpl<DeliveryMethodEntity, DeliveryMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link DeliveryMethodServiceImpl} of the {@link DeliveryMethodEntity} entity.
     */
    public DeliveryMethodRestControllerImpl(AbstractServiceImpl<DeliveryMethodEntity, DeliveryMethodDTO, String> service) {
        super(service);
    }
}

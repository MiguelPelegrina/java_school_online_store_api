package com.javaSchool.FinalTask3.domain.order.deliveryMethod.impl;

import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodDTO;
import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link DeliveryMethodEntity} entity. Handles the REST methods. Uses {@link DeliveryMethodDTO}
 * as returning object.
 */
@RequestMapping(path = "delivery_methods")
@RestController
public class DeliveryMethodRestControllerImpl
        extends AbstractRestControllerImpl<DeliveryMethodServiceImpl, DeliveryMethodRepository, DeliveryMethodEntity, DeliveryMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link DeliveryMethodServiceImpl} of the {@link DeliveryMethodEntity} entity.
     */
    public DeliveryMethodRestControllerImpl(DeliveryMethodServiceImpl service) {
        super(service);
    }
}

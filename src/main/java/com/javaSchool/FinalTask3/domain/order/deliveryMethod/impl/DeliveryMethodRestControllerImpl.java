package com.javaSchool.FinalTask3.domain.order.deliveryMethod.impl;

import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodDTO;
import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodRepository;
import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodRestController;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * RestController of the {@link DeliveryMethodEntity} entity. Handles the REST methods. Uses {@link DeliveryMethodDTO}
 * as returning object.
 */
@RequestMapping(path = "delivery_methods")
@RestController
public class DeliveryMethodRestControllerImpl
        extends AbstractRestControllerImpl<DeliveryMethodServiceImpl, DeliveryMethodRepository, DeliveryMethodEntity, DeliveryMethodDTO, String>
        implements DeliveryMethodRestController {
    /**
     * All arguments constructor.
     * @param service {@link DeliveryMethodServiceImpl} of the {@link DeliveryMethodEntity} entity.
     */
    public DeliveryMethodRestControllerImpl(DeliveryMethodServiceImpl service) {
        super(service);
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<List<DeliveryMethodDTO>> getAllInstances(
            @RequestParam("active") Optional<Boolean> active){
        return ResponseEntity.ok(this.service.getAllInstances(active));
    }
}

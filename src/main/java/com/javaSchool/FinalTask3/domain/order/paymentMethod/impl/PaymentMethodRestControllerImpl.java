package com.javaSchool.FinalTask3.domain.order.paymentMethod.impl;

import com.javaSchool.FinalTask3.domain.order.paymentMethod.PaymentMethodDTO;
import com.javaSchool.FinalTask3.domain.order.paymentMethod.PaymentMethodEntity;
import com.javaSchool.FinalTask3.domain.order.paymentMethod.PaymentMethodRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link PaymentMethodEntity} entity. Handles the REST methods. Uses
 * {@link PaymentMethodDTO} as returning object.
 */
@RequestMapping(path = "payment_methods")
@RestController
public class PaymentMethodRestControllerImpl
        extends AbstractRestControllerImpl<PaymentMethodServiceImpl, PaymentMethodRepository, PaymentMethodEntity, PaymentMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PaymentMethodServiceImpl} of the {@link PaymentMethodEntity} entity.
     */
    public PaymentMethodRestControllerImpl(PaymentMethodServiceImpl service) {
        super(service);
    }
}

package com.javaSchool.FinalTask3.domain.order.paymentMethod;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link PaymentMethodEntity} entity. Handles the REST methods. Uses
 * {@link PaymentMethodDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "paymentmethods")
@RestController
public class PaymentMethodRestControllerImpl extends AbstractRestControllerImpl<PaymentMethodEntity, PaymentMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PaymentMethodServiceImpl} of the {@link PaymentMethodEntity} entity.
     */
    public PaymentMethodRestControllerImpl(AbstractServiceImpl<PaymentMethodEntity, PaymentMethodDTO, String> service) {
        super(service);
    }
}

package com.javaSchool.FinalTask3.domain.paymentMethod;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link PaymentMethodEntity} entity. Handles the REST methods. Uses
 * {@link PaymentMethodDTO} as returning object.
 */
@RequestMapping(path = "paymentmethods", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PaymentMethodRestController extends AbstractRestControllerWithUpdate<PaymentMethodEntity, PaymentMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PaymentMethodService} of the {@link PaymentMethodEntity} entity.
     */
    public PaymentMethodRestController(AbstractServiceWithUpdate<PaymentMethodEntity, PaymentMethodDTO, String> service) {
        super(service);
    }
}
package com.javaSchool.FinalTask3.domain.paymentMethod;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.http.MediaType;
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
public class PaymentMethodRestController extends AbstractRestController<PaymentMethodEntity, PaymentMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PaymentMethodService} of the {@link PaymentMethodEntity} entity.
     */
    public PaymentMethodRestController(AbstractService<PaymentMethodEntity, PaymentMethodDTO, String> service) {
        super(service);
    }
}

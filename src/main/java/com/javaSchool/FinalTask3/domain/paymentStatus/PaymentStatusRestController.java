package com.javaSchool.FinalTask3.domain.paymentStatus;

import com.javaSchool.FinalTask3.utils.AbstractRestControllerWithUpdate;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link PaymentStatusEntity} entity. Handles the REST methods. Uses
 * {@link PaymentStatusDTO} as returning object.
 */
@RequestMapping(path = "paymentstatuses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PaymentStatusRestController extends AbstractRestControllerWithUpdate<PaymentStatusEntity, PaymentStatusDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PaymentStatusService} of the {@link PaymentStatusEntity} entity.
     */
    public PaymentStatusRestController(AbstractServiceWithUpdate<PaymentStatusEntity, PaymentStatusDTO, String> service) {
        super(service);
    }
}

package com.javaSchool.FinalTask3.domain.paymentStatus;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link PaymentStatusEntity} entity. Handles the REST methods. Uses
 * {@link PaymentStatusDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "paymentstatuses")
@RestController
public class PaymentStatusRestController extends AbstractRestController<PaymentStatusEntity, PaymentStatusDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PaymentStatusService} of the {@link PaymentStatusEntity} entity.
     */
    public PaymentStatusRestController(AbstractService<PaymentStatusEntity, PaymentStatusDTO, String> service) {
        super(service);
    }
}

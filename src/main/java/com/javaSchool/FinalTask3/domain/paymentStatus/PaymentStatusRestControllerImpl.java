package com.javaSchool.FinalTask3.domain.paymentStatus;

import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
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
public class PaymentStatusRestControllerImpl extends AbstractRestControllerImpl<PaymentStatusEntity, PaymentStatusDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PaymentStatusServiceImpl} of the {@link PaymentStatusEntity} entity.
     */
    public PaymentStatusRestControllerImpl(AbstractServiceImpl<PaymentStatusEntity, PaymentStatusDTO, String> service) {
        super(service);
    }
}

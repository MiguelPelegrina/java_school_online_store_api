package com.javaSchool.FinalTask3.domain.order.paymentStatus.impl;

import com.javaSchool.FinalTask3.domain.order.paymentStatus.PaymentStatusDTO;
import com.javaSchool.FinalTask3.domain.order.paymentStatus.PaymentStatusEntity;
import com.javaSchool.FinalTask3.domain.order.paymentStatus.PaymentStatusRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link PaymentStatusEntity} entity. Handles the REST methods. Uses
 * {@link PaymentStatusDTO} as returning object.
 */
@RequestMapping(path = "payment_statuses")
@RestController
public class PaymentStatusRestControllerImpl
        extends AbstractRestControllerImpl<PaymentStatusServiceImpl, PaymentStatusRepository, PaymentStatusEntity, PaymentStatusDTO, String> {
    /**
     * All arguments constructor.
     * @param service {@link PaymentStatusServiceImpl} of the {@link PaymentStatusEntity} entity.
     */
    public PaymentStatusRestControllerImpl(PaymentStatusServiceImpl service) {
        super(service);
    }
}

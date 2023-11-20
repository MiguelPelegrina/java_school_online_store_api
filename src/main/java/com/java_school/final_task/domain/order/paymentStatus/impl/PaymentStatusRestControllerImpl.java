package com.java_school.final_task.domain.order.paymentStatus.impl;

import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusDTO;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusEntity;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusRepository;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusRestController;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * RestController of the {@link PaymentStatusEntity} entity. Handles the REST methods. Uses
 * {@link PaymentStatusDTO} as returning object.
 */
@RequestMapping(path = "payment_statuses")
@RestController
public class PaymentStatusRestControllerImpl
        extends AbstractRestControllerImpl<PaymentStatusServiceImpl, PaymentStatusRepository, PaymentStatusEntity, PaymentStatusDTO, String>
        implements PaymentStatusRestController {
    /**
     * All arguments constructor.
     * @param service {@link PaymentStatusServiceImpl} of the {@link PaymentStatusEntity} entity.
     */
    public PaymentStatusRestControllerImpl(PaymentStatusServiceImpl service) {
        super(service);
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<List<PaymentStatusDTO>> getAllInstances(
            @RequestParam("active") Optional<Boolean> active){
        return ResponseEntity.ok(this.service.getAllInstances(active));
    }
}

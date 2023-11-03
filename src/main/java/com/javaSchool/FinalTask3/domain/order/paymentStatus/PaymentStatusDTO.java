package com.javaSchool.FinalTask3.domain.order.paymentStatus;

import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link PaymentStatusEntity}.
 */
@Data
public class PaymentStatusDTO {
    private String name;
    private boolean isActive;
}

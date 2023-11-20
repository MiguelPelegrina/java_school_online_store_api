package com.java_school.final_task.domain.order.paymentStatus;

import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link PaymentStatusEntity}.
 */
@Data
public class PaymentStatusDTO {
    private String name;
    private boolean isActive;
}

package com.java_school.final_task.domain.order.paymentMethod;

import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link PaymentMethodEntity}.
 */
@Data
public class PaymentMethodDTO {
    private String name;
    private boolean isActive;
}

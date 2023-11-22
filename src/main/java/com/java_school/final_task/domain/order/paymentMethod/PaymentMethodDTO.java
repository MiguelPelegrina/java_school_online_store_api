package com.java_school.final_task.domain.order.paymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link PaymentMethodEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentMethodDTO {
    private String name;
    private boolean isActive;
}

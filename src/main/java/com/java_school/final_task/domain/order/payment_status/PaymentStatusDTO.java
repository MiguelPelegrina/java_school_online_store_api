package com.java_school.final_task.domain.order.payment_status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link PaymentStatusEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentStatusDTO {
    private String name;
    private boolean isActive;
}

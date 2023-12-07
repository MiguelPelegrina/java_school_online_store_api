package com.java_school.final_task.domain.order.payment_method;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object of a payment method")
public class PaymentMethodDTO {
    private String name;
    private boolean isActive;
}

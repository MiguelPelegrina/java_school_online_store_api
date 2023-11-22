package com.java_school.final_task.domain.order.deliveryMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link DeliveryMethodEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DeliveryMethodDTO {
    private String name;
    private boolean isActive;
}

package com.java_school.final_task.domain.order.order_status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link OrderStatusEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderStatusDTO {
    private String name;
    private boolean isActive;
}

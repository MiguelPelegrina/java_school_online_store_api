package com.javaSchool.FinalTask3.domain.order.orderStatus;

import lombok.Data;
/**
 * Data Transfer Object (DTO) of {@link OrderStatusEntity}.
 */
@Data
public class OrderStatusDTO {
    private String name;
    private boolean isActive;
}

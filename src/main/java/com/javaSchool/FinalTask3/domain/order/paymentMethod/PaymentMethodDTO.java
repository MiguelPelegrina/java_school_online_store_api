package com.javaSchool.FinalTask3.domain.order.paymentMethod;

import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link PaymentMethodEntity}.
 */
@Data
public class PaymentMethodDTO {
    private String name;
    private boolean isActive;
}

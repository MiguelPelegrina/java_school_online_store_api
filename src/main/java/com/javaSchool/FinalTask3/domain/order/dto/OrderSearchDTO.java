package com.javaSchool.FinalTask3.domain.order.dto;

import com.javaSchool.FinalTask3.domain.order.OrderEntity;
import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.javaSchool.FinalTask3.domain.order.orderStatus.OrderStatusEntity;
import com.javaSchool.FinalTask3.domain.order.paymentMethod.PaymentMethodEntity;
import com.javaSchool.FinalTask3.domain.order.paymentStatus.PaymentStatusEntity;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) to search for {@link OrderEntity}
 */
@Data
public class OrderSearchDTO {
    private LocalDate date;
    private DeliveryMethodEntity deliveryMethod;
    private OrderStatusEntity orderStatus;
    private PaymentMethodEntity paymentMethod;
    private PaymentStatusEntity paymentStatus;
    private String name = "";
    private String sortType;
    private String sortProperty;
    private Integer page = 0;
    private Integer size = 20;
}

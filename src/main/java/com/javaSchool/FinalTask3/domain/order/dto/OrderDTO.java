package com.javaSchool.FinalTask3.domain.order.dto;

import com.javaSchool.FinalTask3.domain.order.OrderEntity;
import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodDTO;
import com.javaSchool.FinalTask3.domain.order.orderStatus.OrderStatusDTO;
import com.javaSchool.FinalTask3.domain.order.paymentMethod.PaymentMethodDTO;
import com.javaSchool.FinalTask3.domain.order.paymentStatus.PaymentStatusDTO;
import com.javaSchool.FinalTask3.domain.orderBook.OrderBookJsonDTO;
import com.javaSchool.FinalTask3.domain.user.UserDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object (DTO) of {@link OrderEntity}.
 */
@Data
public class OrderDTO {
    private int id;
    private UserDTO user;
    private DeliveryMethodDTO deliveryMethod;
    private OrderStatusDTO orderStatus;
    private PaymentMethodDTO paymentMethod;
    private PaymentStatusDTO paymentStatus;
    private List<OrderBookJsonDTO> orderedBooks;
    private LocalDate date;
}

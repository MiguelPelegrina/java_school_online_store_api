package com.java_school.final_task.domain.order.dto;

import com.java_school.final_task.domain.order.OrderEntity;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodDTO;
import com.java_school.final_task.domain.order.orderStatus.OrderStatusDTO;
import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodDTO;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusDTO;
import com.java_school.final_task.domain.orderBook.OrderBookJsonDTO;
import com.java_school.final_task.domain.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object (DTO) of {@link OrderEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private UserDTO user;
    private DeliveryMethodDTO deliveryMethod;
    private OrderStatusDTO orderStatus;
    private PaymentMethodDTO paymentMethod;
    private PaymentStatusDTO paymentStatus;
    private LocalDate date;
    private List<OrderBookJsonDTO> orderedBooks;
}

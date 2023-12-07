package com.java_school.final_task.domain.order.dto;

import com.java_school.final_task.domain.order.OrderEntity;
import com.java_school.final_task.domain.order.delivery_method.DeliveryMethodDTO;
import com.java_school.final_task.domain.order.order_status.OrderStatusDTO;
import com.java_school.final_task.domain.order.payment_method.PaymentMethodDTO;
import com.java_school.final_task.domain.order.payment_status.PaymentStatusDTO;
import com.java_school.final_task.domain.order_book.OrderBookJsonDTO;
import com.java_school.final_task.domain.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object of a order")
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

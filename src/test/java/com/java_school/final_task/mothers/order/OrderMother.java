package com.java_school.final_task.mothers.order;

import com.java_school.final_task.domain.order.OrderEntity;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodDTO;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.orderStatus.OrderStatusDTO;
import com.java_school.final_task.domain.order.orderStatus.OrderStatusEntity;
import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodDTO;
import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodEntity;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusDTO;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusEntity;
import com.java_school.final_task.domain.orderBook.OrderBookJsonDTO;
import com.java_school.final_task.mothers.book.BookMother;
import com.java_school.final_task.mothers.order_book.OrderBookMother;
import com.java_school.final_task.mothers.user.UserMother;

import java.time.LocalDate;
import java.util.List;

public class OrderMother {
    public static OrderEntity createOrder(){
        return OrderEntity.builder()
                .id(0)
                .orderedBooks(null)
                .date(LocalDate.now())
                .deliveryMethod(DeliveryMethodEntity.builder().name("DeliveryMethod").isActive(true).build())
                .orderStatus(OrderStatusEntity.builder().name("OrderStatus").isActive(true).build())
                .paymentMethod(PaymentMethodEntity.builder().name("PaymentMethod").isActive(true).build())
                .paymentStatus(PaymentStatusEntity.builder().name("PaymentStatus").isActive(true).build())
                .orderedBooks(List.of(OrderBookMother.createOrderBook()))
                .user(UserMother.createUser())
                .build();
    }

    public static OrderDTO createOrderDTO(){
        return OrderDTO.builder()
                .id(0)
                .user(UserMother.createUserDTO())
                .date(LocalDate.now())
                .deliveryMethod(DeliveryMethodDTO.builder().name("DeliveryMethod").isActive(true).build())
                .orderStatus(OrderStatusDTO.builder().name("OrderStatus").isActive(true).build())
                .paymentMethod(PaymentMethodDTO.builder().name("PaymentMethod").isActive(true).build())
                .paymentStatus(PaymentStatusDTO.builder().name("PaymentStatus").isActive(true).build())
                .orderedBooks(List.of(createOrderBookJsonDTO()))
                .build();
    }

    public static OrderBookJsonDTO createOrderBookJsonDTO(){
        return OrderBookJsonDTO.builder()
                .id(1)
                .book(BookMother.createBookDTO())
                .amount(1)
                .build();
    }
}

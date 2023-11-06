package com.javaSchool.FinalTask3.domain.order.dto;

import com.javaSchool.FinalTask3.domain.order.OrderEntity;
import com.javaSchool.FinalTask3.domain.orderBook.OrderBookDTO;
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
    private String deliveryMethod;
    private String orderStatus;
    private String paymentMethod;
    private String paymentStatus;
    private List<OrderBookDTO> orderedBook;
    private LocalDate date;
}

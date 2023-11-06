package com.javaSchool.FinalTask3.domain.order.dto;

import com.javaSchool.FinalTask3.domain.order.OrderEntity;
import com.javaSchool.FinalTask3.domain.orderBook.OrderBookEntity;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) of for saving orders.
 */
@Data
public class SaveOrderDTO {
    private OrderEntity order;
    private List<OrderBookEntity> orderedBooks;
}

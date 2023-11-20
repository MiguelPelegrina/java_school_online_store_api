package com.java_school.final_task.domain.order.dto;

import com.java_school.final_task.domain.order.OrderEntity;
import com.java_school.final_task.domain.orderBook.OrderBookEntity;
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

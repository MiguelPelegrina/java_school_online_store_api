package com.java_school.final_task.domain.order.dto;

import com.java_school.final_task.domain.order.OrderEntity;
import com.java_school.final_task.domain.order_book.OrderBookEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) of for saving orders.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Schema(description = "Data Transfer Object of a saved order without a circular reference")
public class SaveOrderDTO {
    private OrderEntity order;
    private List<OrderBookEntity> orderedBooks;
}

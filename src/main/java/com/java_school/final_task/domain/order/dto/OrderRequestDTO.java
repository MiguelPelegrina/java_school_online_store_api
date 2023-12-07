package com.java_school.final_task.domain.order.dto;

import com.java_school.final_task.domain.order.OrderEntity;
import com.java_school.final_task.domain.order.OrderRepository;
import com.java_school.final_task.utils.AbstractPageableSortableRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Request class for {@link OrderEntity}. Contains the specified parameters and sorting criteria to search for in the
 * {@link OrderRepository}.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "A request to retrieve a filtered list of orders")
public class OrderRequestDTO extends AbstractPageableSortableRequest {
    private String name = "";
    private LocalDate date;
    private String deliveryMethod = "";
    private String orderStatus = "";
    private String paymentMethod = "";
    private String paymentStatus = "";
}

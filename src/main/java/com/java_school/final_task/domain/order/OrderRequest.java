package com.java_school.final_task.domain.order;

import com.java_school.final_task.utils.AbstractPageableSortableRequest;
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
public class OrderRequest extends AbstractPageableSortableRequest {
    private String name = "";
    private LocalDate date;
    private String deliveryMethod = "";
    private String orderStatus = "";
    private String paymentMethod = "";
    private String paymentStatus = "";
}

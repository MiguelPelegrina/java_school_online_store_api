package com.java_school.final_task.domain.order_book;

import com.java_school.final_task.domain.book.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link OrderBookEntity}, without reference to order, to not create recursive JSON.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderBookJsonDTO {
    private int id;
    private BookDTO book;
    private int amount;
}

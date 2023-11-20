package com.java_school.final_task.domain.orderBook;

import com.java_school.final_task.domain.book.dto.BookDTO;
import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link OrderBookEntity}.
 */
@Data
public class OrderBookDTO {
    private int id;
    // TODO Not sure if right
    //private OrderDTO order;
    // TODO Not sure if right
    private BookDTO book;
    private int amount;
}

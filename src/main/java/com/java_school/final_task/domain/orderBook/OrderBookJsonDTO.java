package com.java_school.final_task.domain.orderBook;

import com.java_school.final_task.domain.book.dto.BookDTO;
import lombok.Data;
/**
 * Data Transfer Object (DTO) of {@link OrderBookEntity}, without reference to order, to not create recursive JSON.
 */
@Data
public class OrderBookJsonDTO {
    private int id;
    private BookDTO book;
    private int amount;
}

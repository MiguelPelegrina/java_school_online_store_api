package com.javaSchool.FinalTask3.domain.orderBook;

import com.javaSchool.FinalTask3.domain.book.BookDTO;
import com.javaSchool.FinalTask3.domain.order.dto.OrderDTO;
import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link OrderBookEntity}.
 */
@Data
public class OrderBookDTO {
    private int id;
    // TODO Not sure if right
    private OrderDTO order;
    // TODO Not sure if right
    private BookDTO book;
    private int amount;
}

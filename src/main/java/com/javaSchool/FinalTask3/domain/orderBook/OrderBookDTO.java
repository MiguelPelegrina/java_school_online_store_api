package com.javaSchool.FinalTask3.domain.orderBook;

import com.javaSchool.FinalTask3.domain.book.BookDTO;
import com.javaSchool.FinalTask3.domain.order.OrderDTO;
import com.javaSchool.FinalTask3.domain.orderBook.embedabble.OrderBookId;
import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link OrderBookEntity}.
 */
@Data
public class OrderBookDTO {
    private OrderBookId id;
    // TODO Not sure if right
    private OrderDTO order;
    // TODO Not sure if right
    private BookDTO book;
    private int amount;
}

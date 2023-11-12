package com.javaSchool.FinalTask3.domain.orderBook;

import com.javaSchool.FinalTask3.domain.book.dto.BookDTO;
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

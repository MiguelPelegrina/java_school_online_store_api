package com.javaSchool.FinalTask3.dtos;

import com.javaSchool.FinalTask3.entities.embeddables.OrderBookId;
import lombok.Data;

@Data
public class OrderBookDTO {
    private OrderBookId id;
    // TODO Not sure if right
    private OrderDTO order;
    // TODO Not sure if right
    private BookDTO book;
    private int amount;
}

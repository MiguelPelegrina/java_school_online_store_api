package com.java_school.final_task.domain.orderBook.impl;

import com.java_school.final_task.domain.orderBook.OrderBookDTO;
import com.java_school.final_task.domain.orderBook.OrderBookEntity;
import com.java_school.final_task.domain.orderBook.OrderBookRepository;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link OrderBookEntity} entity. Handles the REST methods. Uses
 * {@link OrderBookDTO} as returning object.
 */
@RequestMapping(path = "order_books")
@RestController
public class OrderBookRestControllerImpl
        extends AbstractRestControllerImpl<OrderBookServiceImpl, OrderBookRepository, OrderBookEntity, OrderBookDTO, Integer> {
    /**
     * All arguments constructor.
     * @param service {@link OrderBookServiceImpl} of the {@link OrderBookEntity} entity.
     */
    public OrderBookRestControllerImpl(OrderBookServiceImpl service) {
        super(service);
    }
}

package com.javaSchool.FinalTask3.domain.orderBook.impl;

import com.javaSchool.FinalTask3.domain.orderBook.OrderBookDTO;
import com.javaSchool.FinalTask3.domain.orderBook.OrderBookEntity;
import com.javaSchool.FinalTask3.domain.orderBook.OrderBookRepository;
import com.javaSchool.FinalTask3.domain.orderBook.embedabble.OrderBookId;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link OrderBookEntity} entity. Handles the REST methods. Uses
 * {@link OrderBookDTO} as returning object.
 */
@RequestMapping(path = "order_books")
@RestController
public class OrderBookRestControllerImpl
        extends AbstractRestControllerImpl<OrderBookServiceImpl, OrderBookRepository, OrderBookEntity, OrderBookDTO, OrderBookId> {
    /**
     * All arguments constructor.
     * @param service {@link OrderBookServiceImpl} of the {@link OrderBookEntity} entity.
     */
    public OrderBookRestControllerImpl(OrderBookServiceImpl service) {
        super(service);
    }
}

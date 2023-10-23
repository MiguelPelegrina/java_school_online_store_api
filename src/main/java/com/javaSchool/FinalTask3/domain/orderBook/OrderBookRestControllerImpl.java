package com.javaSchool.FinalTask3.domain.orderBook;

import com.javaSchool.FinalTask3.domain.orderBook.embedabble.OrderBookId;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link OrderBookEntity} entity. Handles the REST methods. Uses
 * {@link OrderBookDTO} as returning object.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "orderbooks")
@RestController
public class OrderBookRestControllerImpl extends AbstractRestControllerImpl<OrderBookEntity, OrderBookDTO, OrderBookId> {
    /**
     * All arguments constructor.
     * @param service {@link OrderBookServiceImpl} of the {@link OrderBookEntity} entity.
     */
    public OrderBookRestControllerImpl(AbstractServiceImpl<OrderBookEntity, OrderBookDTO, OrderBookId> service) {
        super(service);
    }
}

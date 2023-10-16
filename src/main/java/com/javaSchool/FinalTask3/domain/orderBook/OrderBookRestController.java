package com.javaSchool.FinalTask3.domain.orderBook;

import com.javaSchool.FinalTask3.domain.orderBook.embedabble.OrderBookId;
import com.javaSchool.FinalTask3.utils.AbstractRestController;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link OrderBookEntity} entity. Handles the REST methods. Uses
 * {@link OrderBookDTO} as returning object.
 */
@RequestMapping(path = "orderbooks", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class OrderBookRestController extends AbstractRestController<OrderBookEntity, OrderBookDTO, OrderBookId> {
    /**
     * All arguments constructor.
     * @param service {@link OrderBookService} of the {@link OrderBookEntity} entity.
     */
    public OrderBookRestController(AbstractService<OrderBookEntity, OrderBookDTO, OrderBookId> service) {
        super(service);
    }
}

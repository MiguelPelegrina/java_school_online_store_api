package com.javaSchool.FinalTask3.domain.orderBook;

import com.javaSchool.FinalTask3.domain.orderBook.embedabble.OrderBookId;
import com.javaSchool.FinalTask3.utils.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link OrderBookRepository} and the
 * {@link OrderBookRestController}. Obtains data from the
 * {@link OrderBookRepository} and returns the object(s) of the entity {@link OrderBookEntity} as
 * {@link OrderBookDTO} to the {@link OrderBookRestController}.
 */
@Service
@Transactional(readOnly = true)
public class OrderBookService extends AbstractService<OrderBookEntity, OrderBookDTO, OrderBookId> {
    /**
     * All arguments constructor.
     * @param repository {@link OrderBookRepository} of the {@link OrderBookEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link OrderBookEntity} to {@link OrderBookDTO}
     */
    public OrderBookService(OrderBookRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link OrderBookEntity} entity.
     * @return Returns the {@link OrderBookDTO} class.
     */
    @Override
    protected Class<OrderBookDTO> getDTOClass() {
        return OrderBookDTO.class;
    }

    @Override
    protected OrderBookId getEntityId(OrderBookEntity instance) {
        return instance.getId();
    }
}

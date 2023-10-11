package com.javaSchool.FinalTask3.domain.orderBook;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
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
public class OrderBookService extends AbstractServiceWithUpdate<OrderBookEntity, OrderBookDTO, OrderBookId> {
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

    /**
     * Updates the values of an existing {@link OrderBookEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(OrderBookEntity existingInstance, OrderBookEntity newInstance) {
        existingInstance.setAmount(newInstance.getAmount());
        existingInstance.setBook(newInstance.getBook());
        existingInstance.setOrder(newInstance.getOrder());
    }
}

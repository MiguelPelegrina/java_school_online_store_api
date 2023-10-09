package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.OrderBookDTO;
import com.javaSchool.FinalTask3.entities.OrderBook;
import com.javaSchool.FinalTask3.entities.embeddables.OrderBookId;
import com.javaSchool.FinalTask3.repositories.OrderBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link OrderBookRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.OrderBookController}. Obtains data from the
 * {@link OrderBookRepository} and returns the object(s) of the entity {@link OrderBook} as
 * {@link OrderBookDTO} to the {@link com.javaSchool.FinalTask3.controller.OrderBookController}.
 */
@Service
@Transactional(readOnly = true)
public class OrderBookService extends BaseServiceWithUpdate<OrderBook, OrderBookDTO, OrderBookId>{
    /**
     * All arguments constructor.
     * @param repository {@link OrderBookRepository} of the {@link OrderBook} entity.
     * @param modelMapper ModelMapper that converts the {@link OrderBook} to {@link OrderBookDTO}
     */
    public OrderBookService(OrderBookRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link OrderBook} entity.
     * @return Returns the {@link OrderBookDTO} class.
     */
    @Override
    protected Class<OrderBookDTO> getDTOClass() {
        return OrderBookDTO.class;
    }

    /**
     * Updates the values of an existing {@link OrderBook} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(OrderBook existingInstance, OrderBook newInstance) {
        existingInstance.setAmount(newInstance.getAmount());
        existingInstance.setBook(newInstance.getBook());
        existingInstance.setOrder(newInstance.getOrder());
    }
}

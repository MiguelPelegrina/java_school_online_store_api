package com.javaSchool.FinalTask3.domain.orderBook.impl;

import com.javaSchool.FinalTask3.domain.orderBook.OrderBookDTO;
import com.javaSchool.FinalTask3.domain.orderBook.OrderBookEntity;
import com.javaSchool.FinalTask3.domain.orderBook.OrderBookRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link OrderBookRepository} and the
 * {@link OrderBookRestControllerImpl}. Obtains data from the {@link OrderBookRepository} and returns the object(s) of
 * the entity {@link OrderBookEntity} as {@link OrderBookDTO} to the {@link OrderBookRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class OrderBookServiceImpl
        extends AbstractServiceImpl<OrderBookRepository, OrderBookEntity, OrderBookDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link OrderBookRepository} of the {@link OrderBookEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link OrderBookEntity} to {@link OrderBookDTO}
     */
    public OrderBookServiceImpl(OrderBookRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<OrderBookDTO> getDTOClass() {
        return OrderBookDTO.class;
    }

    @Override
    public Integer getEntityId(OrderBookEntity instance) {
        return instance.getId();
    }
}

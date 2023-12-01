package com.java_school.final_task.domain.order_book.impl;

import com.java_school.final_task.domain.order_book.OrderBookDTO;
import com.java_school.final_task.domain.order_book.OrderBookEntity;
import com.java_school.final_task.domain.order_book.OrderBookRepository;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for the interaction between the {@link OrderBookRepository} and the
 * {@link OrderBookRestControllerImpl}. Obtains data from the {@link OrderBookRepository} and returns the object(s) of
 * the entity {@link OrderBookEntity} as {@link OrderBookDTO} to the {@link OrderBookRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
@Service
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

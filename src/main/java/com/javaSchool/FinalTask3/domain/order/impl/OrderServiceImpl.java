package com.javaSchool.FinalTask3.domain.order.impl;

import com.javaSchool.FinalTask3.domain.order.OrderDTO;
import com.javaSchool.FinalTask3.domain.order.OrderEntity;
import com.javaSchool.FinalTask3.domain.order.OrderRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link OrderRepository} and the {@link OrderRestControllerImpl}.
 * Obtains data from the {@link OrderRepository} and returns the object(s) of the {@link OrderEntity} as {@link OrderDTO}
 * to the {@link OrderRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl
        extends AbstractServiceImpl<OrderRepository, OrderEntity, OrderDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link OrderRepository} of the {@link OrderEntity}.
     * @param modelMapper ModelMapper that converts the {@link OrderEntity} instance to {@link OrderDTO}
     */
    public OrderServiceImpl(OrderRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link OrderEntity}.
     * @return Returns the {@link OrderDTO} class.
     */
    @Override
    protected Class<OrderDTO> getDTOClass() {
        return OrderDTO.class;
    }

    @Override
    protected Integer getEntityId(OrderEntity instance) {
        return instance.getId();
    }
}

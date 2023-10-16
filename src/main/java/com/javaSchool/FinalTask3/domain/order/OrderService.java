package com.javaSchool.FinalTask3.domain.order;

import com.javaSchool.FinalTask3.utils.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link OrderRepository} and the {@link OrderRestController}.
 * Obtains data from the {@link OrderRepository} and returns the object(s) of the {@link OrderEntity} as {@link OrderDTO}
 * to the {@link OrderRestController}.
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends AbstractService<OrderEntity, OrderDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link OrderRepository} of the {@link OrderEntity}.
     * @param modelMapper ModelMapper that converts the {@link OrderEntity} instance to {@link OrderDTO}
     */
    public OrderService(OrderRepository repository, ModelMapper modelMapper) {
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

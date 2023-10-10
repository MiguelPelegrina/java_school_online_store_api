package com.javaSchool.FinalTask3.domain.order;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link OrderRepository} and the {@link OrderController}.
 * Obtains data from the {@link OrderRepository} and returns the object(s) of the {@link OrderEntity} as {@link OrderDTO}
 * to the {@link OrderController}.
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends AbstractServiceWithUpdate<OrderEntity, OrderDTO, Integer> {
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

    /**
     * Updates the values of an existing {@link OrderEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(OrderEntity existingInstance, OrderEntity newInstance) {
        existingInstance.setDate(newInstance.getDate());
        existingInstance.setDeliveryMethod(newInstance.getDeliveryMethod());
        existingInstance.setPaymentMethod(newInstance.getPaymentMethod());
        existingInstance.setPaymentStatus(newInstance.getPaymentStatus());
        existingInstance.setOrderStatus(newInstance.getOrderStatus());
        existingInstance.setUser(newInstance.getUser());
    }
}

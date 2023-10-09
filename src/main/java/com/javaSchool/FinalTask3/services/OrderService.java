package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.OrderDTO;
import com.javaSchool.FinalTask3.entities.Order;
import com.javaSchool.FinalTask3.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link OrderRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.OrderController}. Obtains data from the
 * {@link OrderRepository} and returns the object(s) of the entity {@link Order} as
 * {@link OrderDTO} to the {@link com.javaSchool.FinalTask3.controller.OrderController}.
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends BaseServiceWithUpdate<Order, OrderDTO, Integer>{
    /**
     * All arguments constructor.
     * @param repository {@link OrderRepository} of the {@link Order} entity.
     * @param modelMapper ModelMapper that converts the {@link Order} to {@link OrderDTO}
     */
    public OrderService(OrderRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link Order} entity.
     * @return Returns the {@link OrderDTO} class.
     */
    @Override
    protected Class<OrderDTO> getDTOClass() {
        return OrderDTO.class;
    }

    /**
     * Updates the values of an existing {@link Order} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(Order existingInstance, Order newInstance) {
        existingInstance.setDate(newInstance.getDate());
        existingInstance.setDeliveryMethod(newInstance.getDeliveryMethod());
        existingInstance.setPaymentMethod(newInstance.getPaymentMethod());
        existingInstance.setPaymentStatus(newInstance.getPaymentStatus());
        existingInstance.setOrderStatus(newInstance.getOrderStatus());
        existingInstance.setUser(newInstance.getUser());
    }
}

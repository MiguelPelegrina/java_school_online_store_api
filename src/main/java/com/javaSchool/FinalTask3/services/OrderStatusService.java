package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookGenreDTO;
import com.javaSchool.FinalTask3.dtos.OrderStatusDTO;
import com.javaSchool.FinalTask3.entities.BookGenre;
import com.javaSchool.FinalTask3.entities.Country;
import com.javaSchool.FinalTask3.entities.OrderStatus;
import com.javaSchool.FinalTask3.repositories.BookGenreRepository;
import com.javaSchool.FinalTask3.repositories.OrderStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link OrderStatusRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.OrderStatusController}. Obtains data from the {@link OrderStatusRepository}
 * and returns the object(s) of the entity {@link OrderStatus} as {@link OrderStatusDTO} to the
 * {@link com.javaSchool.FinalTask3.controller.OrderStatusController}.
 */
@Service
@Transactional(readOnly = true)
public class OrderStatusService extends BaseServiceWithUpdate<OrderStatus, OrderStatusDTO, String>{
    /**
     * All arguments constructor.
     * @param repository {@link OrderStatusRepository} of the {@link OrderStatus} entity.
     * @param modelMapper ModelMapper that converts the {@link OrderStatus} to {@link OrderStatusDTO}
     */
    public OrderStatusService(OrderStatusRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }


    /**
     * Returns the DTO class of the {@link OrderStatusDTO} entity.
     * @return Returns the {@link OrderStatusDTO} class.
     */
    @Override
    protected Class<OrderStatusDTO> getDTOClass() {
        return OrderStatusDTO.class;
    }

    /**
     * Updates the values of an existing {@link OrderStatus} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(OrderStatus existingInstance, OrderStatus newInstance) {
        existingInstance.setActive(newInstance.isActive());
    }
}

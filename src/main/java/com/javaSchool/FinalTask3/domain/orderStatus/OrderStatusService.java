package com.javaSchool.FinalTask3.domain.orderStatus;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link OrderStatusRepository} and the
 * {@link OrderStatusRestController}. Obtains data from the {@link OrderStatusRepository}
 * and returns the object(s) of the entity {@link OrderStatusEntity} as {@link OrderStatusDTO} to the
 * {@link OrderStatusRestController}.
 */
@Service
@Transactional(readOnly = true)
public class OrderStatusService extends AbstractServiceWithUpdate<OrderStatusEntity, OrderStatusDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link OrderStatusRepository} of the {@link OrderStatusEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link OrderStatusEntity} to {@link OrderStatusDTO}
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

    @Override
    protected String getEntityId(OrderStatusEntity instance) {
        return instance.getName();
    }

    /**
     * Updates the values of an existing {@link OrderStatusEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(OrderStatusEntity existingInstance, OrderStatusEntity newInstance) {
        existingInstance.setActive(newInstance.isActive());
    }
}

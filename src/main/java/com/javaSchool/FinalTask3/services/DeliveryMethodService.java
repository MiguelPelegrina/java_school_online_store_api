package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.BookGenreDTO;
import com.javaSchool.FinalTask3.dtos.DeliveryMethodDTO;
import com.javaSchool.FinalTask3.entities.DeliveryMethod;
import com.javaSchool.FinalTask3.repositories.DeliveryMethodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link DeliveryMethodRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.DeliveryMethodController}. Obtains data from the
 * {@link DeliveryMethodRepository} and returns the object(s) of the entity {@link DeliveryMethod} as
 * {@link DeliveryMethodDTO} to the {@link com.javaSchool.FinalTask3.controller.DeliveryMethodController}.
 */
@Service
@Transactional(readOnly = true)
public class DeliveryMethodService extends BaseServiceWithUpdate<DeliveryMethod, DeliveryMethodDTO, String>{
    /**
     * All arguments constructor.
     * @param repository {@link DeliveryMethodRepository} of the {@link DeliveryMethod} entity.
     * @param modelMapper ModelMapper that converts the {@link DeliveryMethod} to {@link BookGenreDTO}
     */
    public DeliveryMethodService(DeliveryMethodRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link DeliveryMethod} entity.
     * @return Returns the {@link DeliveryMethodDTO} class.
     */
    @Override
    protected Class<DeliveryMethodDTO> getDTOClass() {
        return DeliveryMethodDTO.class;
    }

    /**
     * Updates the values of an existing {@link DeliveryMethod} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(DeliveryMethod existingInstance, DeliveryMethod newInstance) {
        existingInstance.setActive(newInstance.isActive());
    }
}

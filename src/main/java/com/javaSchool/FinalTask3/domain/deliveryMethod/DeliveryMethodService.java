package com.javaSchool.FinalTask3.domain.deliveryMethod;

import com.javaSchool.FinalTask3.domain.bookGenre.BookGenreDTO;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link DeliveryMethodRepository} and the
 * {@link DeliveryMethodRestController}. Obtains data from the
 * {@link DeliveryMethodRepository} and returns the object(s) of the entity {@link DeliveryMethodEntity} as
 * {@link DeliveryMethodDTO} to the {@link DeliveryMethodRestController}.
 */
@Service
@Transactional(readOnly = true)
public class DeliveryMethodService extends AbstractServiceWithUpdate<DeliveryMethodEntity, DeliveryMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link DeliveryMethodRepository} of the {@link DeliveryMethodEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link DeliveryMethodEntity} to {@link BookGenreDTO}
     */
    public DeliveryMethodService(DeliveryMethodRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link DeliveryMethodEntity} entity.
     * @return Returns the {@link DeliveryMethodDTO} class.
     */
    @Override
    protected Class<DeliveryMethodDTO> getDTOClass() {
        return DeliveryMethodDTO.class;
    }

    @Override
    protected String getEntityId(DeliveryMethodEntity instance) {
        return instance.getName();
    }

    /**
     * Updates the values of an existing {@link DeliveryMethodEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(DeliveryMethodEntity existingInstance, DeliveryMethodEntity newInstance) {
        existingInstance.setActive(newInstance.isActive());
    }
}

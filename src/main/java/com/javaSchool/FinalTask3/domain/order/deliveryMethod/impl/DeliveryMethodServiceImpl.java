package com.javaSchool.FinalTask3.domain.order.deliveryMethod.impl;

import com.javaSchool.FinalTask3.domain.book.genre.BookGenreDTO;
import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodDTO;
import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link DeliveryMethodRepository} and the
 * {@link DeliveryMethodRestControllerImpl}. Obtains data from the
 * {@link DeliveryMethodRepository} and returns the object(s) of the entity {@link DeliveryMethodEntity} as
 * {@link DeliveryMethodDTO} to the {@link DeliveryMethodRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class DeliveryMethodServiceImpl
        extends AbstractServiceImpl<DeliveryMethodRepository, DeliveryMethodEntity, DeliveryMethodDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link DeliveryMethodRepository} of the {@link DeliveryMethodEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link DeliveryMethodEntity} to {@link BookGenreDTO}
     */
    public DeliveryMethodServiceImpl(DeliveryMethodRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<DeliveryMethodDTO> getDTOClass() {
        return DeliveryMethodDTO.class;
    }

    @Override
    public String getEntityId(DeliveryMethodEntity instance) {
        return instance.getName();
    }
}

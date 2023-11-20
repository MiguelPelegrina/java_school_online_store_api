package com.java_school.final_task.domain.order.deliveryMethod.impl;

import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.order.deliveryMethod.*;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link DeliveryMethodRepository} and the
 * {@link DeliveryMethodRestControllerImpl}. Obtains data from the
 * {@link DeliveryMethodRepository} and returns the object(s) of the entity {@link DeliveryMethodEntity} as
 * {@link DeliveryMethodDTO} to the {@link DeliveryMethodRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
@Service
public class DeliveryMethodServiceImpl
        extends AbstractServiceImpl<DeliveryMethodRepository, DeliveryMethodEntity, DeliveryMethodDTO, String>
implements DeliveryMethodService {
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

    @Override
    public List<DeliveryMethodDTO> getAllInstances(Optional<Boolean> active) {
        // Variables
        final QDeliveryMethodEntity qDeliveryMethod = QDeliveryMethodEntity.deliveryMethodEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        active.ifPresent(aBoolean -> queryBuilder.and(qDeliveryMethod.isActive.eq(aBoolean)));

        // Retrieve a list of delivery methods from the repository, map them to DTOs, and collect them into a List.
        return this.repository.findAll(queryBuilder).stream().map(
                deliveryMethod -> modelMapper.map(deliveryMethod, getDTOClass()))
                .collect(Collectors.toList());
    }
}

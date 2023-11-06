package com.javaSchool.FinalTask3.domain.order.orderStatus.impl;

import com.javaSchool.FinalTask3.domain.order.orderStatus.*;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link OrderStatusRepository} and the
 * {@link OrderStatusRestControllerImpl}. Obtains data from the {@link OrderStatusRepository}
 * and returns the object(s) of the entity {@link OrderStatusEntity} as {@link OrderStatusDTO} to the
 * {@link OrderStatusRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class OrderStatusServiceImpl
        extends AbstractServiceImpl<OrderStatusRepository, OrderStatusEntity, OrderStatusDTO, String>
        implements OrderStatusService {
    /**
     * All arguments constructor.
     * @param repository {@link OrderStatusRepository} of the {@link OrderStatusEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link OrderStatusEntity} to {@link OrderStatusDTO}
     */
    public OrderStatusServiceImpl(OrderStatusRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<OrderStatusDTO> getDTOClass() {
        return OrderStatusDTO.class;
    }

    @Override
    public String getEntityId(OrderStatusEntity instance) {
        return instance.getName();
    }

    @Override
    public List<OrderStatusDTO> getAllInstances(Optional<Boolean> active) {
        // Variables
        final QOrderStatusEntity qOrderStatusEntity = QOrderStatusEntity.orderStatusEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        active.ifPresent(aBoolean -> queryBuilder.and(qOrderStatusEntity.isActive.eq(aBoolean)));

        // Retrieve a list of order statuses from the repository, map them to DTOs, and collect them into a List.
        return this.repository.findAll(queryBuilder).stream().map(
                        orderStatus -> modelMapper.map(orderStatus, getDTOClass()))
                .collect(Collectors.toList());
    }
}

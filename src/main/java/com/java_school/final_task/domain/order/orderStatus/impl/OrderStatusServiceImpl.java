package com.java_school.final_task.domain.order.orderStatus.impl;

import com.javaSchool.finalTask.domain.order.orderStatus.*;
import com.java_school.final_task.domain.order.orderStatus.*;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link OrderStatusRepository} and the
 * {@link OrderStatusRestControllerImpl}. Obtains data from the {@link OrderStatusRepository}
 * and returns the object(s) of the entity {@link OrderStatusEntity} as {@link OrderStatusDTO} to the
 * {@link OrderStatusRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
@Service
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

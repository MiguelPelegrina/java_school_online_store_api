package com.javaSchool.FinalTask3.domain.order.impl;

import com.javaSchool.FinalTask3.domain.order.OrderService;
import com.javaSchool.FinalTask3.domain.order.QOrderEntity;
import com.javaSchool.FinalTask3.domain.order.dto.OrderDTO;
import com.javaSchool.FinalTask3.domain.order.dto.SaveOrderDTO;
import com.javaSchool.FinalTask3.domain.order.OrderEntity;
import com.javaSchool.FinalTask3.domain.order.OrderRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Service class responsible for the interaction between the {@link OrderRepository} and the {@link OrderRestControllerImpl}.
 * Obtains data from the {@link OrderRepository} and returns the object(s) of the {@link OrderEntity} as {@link OrderDTO}
 * to the {@link OrderRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "EMPLOYEE", "CLIENT"})
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl
        extends AbstractServiceImpl<OrderRepository, OrderEntity, OrderDTO, Integer>
        implements OrderService {
    /**
     * All arguments constructor.
     * @param repository {@link OrderRepository} of the {@link OrderEntity}.
     * @param modelMapper ModelMapper that converts the {@link OrderEntity} instance to {@link OrderDTO}
     */
    public OrderServiceImpl(OrderRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<OrderDTO> getDTOClass() {
        return OrderDTO.class;
    }

    @Override
    public Integer getEntityId(OrderEntity instance) {
        return instance.getId();
    }

    @Override
    public Page<OrderDTO> getAllInstances(LocalDate date, String deliveryMethod, String orderStatus,
                                          String paymentMethod, String paymentStatus, String name, PageRequest pageRequest) {
        // Variables
        final QOrderEntity qOrder = QOrderEntity.orderEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        if(!name.isEmpty()){
            queryBuilder.and(qOrder.user.name.containsIgnoreCase(name)
                    .or(qOrder.user.surname.containsIgnoreCase(name))
                    .or(qOrder.user.id.eq(Integer.parseInt(name))));
        }

        if(!deliveryMethod.isEmpty()){
            queryBuilder.and(qOrder.deliveryMethod.name.containsIgnoreCase(deliveryMethod));
        }

        if(!orderStatus.isEmpty()){
            queryBuilder.and(qOrder.orderStatus.name.containsIgnoreCase(orderStatus));
        }

        if(!paymentMethod.isEmpty()){
            queryBuilder.and(qOrder.paymentMethod.name.containsIgnoreCase(paymentMethod));
        }

        if(!paymentStatus.isEmpty()){
            queryBuilder.and(qOrder.paymentStatus.name.containsIgnoreCase(paymentStatus));
        }

        if(date != null){
            queryBuilder.and(qOrder.date.eq(date));
        }

        // Find the data in the repository
        Page<OrderEntity> pageEntities = this.repository.findAll(queryBuilder, pageRequest);

        // Convert the oder page to a orderDTO page
        return pageEntities.map(order -> modelMapper.map(order, this.getDTOClass()));
    }

    @Override
    @Transactional()
    public OrderDTO saveInstance(SaveOrderDTO saveOrderDTO)  {
        // Build an order
        OrderEntity newOrder = OrderEntity.builder()
                .date(saveOrderDTO.getOrder().getDate())
                .orderStatus(saveOrderDTO.getOrder().getOrderStatus())
                .paymentStatus(saveOrderDTO.getOrder().getPaymentStatus())
                .paymentMethod(saveOrderDTO.getOrder().getPaymentMethod())
                .deliveryMethod(saveOrderDTO.getOrder().getDeliveryMethod())
                .user(saveOrderDTO.getOrder().getUser())
                .orderedBooks(saveOrderDTO.getOrderedBooks())
                .build();

        // Set the order of the books
        saveOrderDTO.getOrderedBooks().forEach(orderedBook ->
            orderedBook.setOrder(newOrder)
        );

        return modelMapper.map(repository.save(newOrder), getDTOClass());
    }
}

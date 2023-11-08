package com.javaSchool.FinalTask3.domain.order.impl;

import com.javaSchool.FinalTask3.domain.order.*;
import com.javaSchool.FinalTask3.domain.order.dto.OrderDTO;
import com.javaSchool.FinalTask3.domain.order.dto.SaveOrderDTO;
import com.javaSchool.FinalTask3.domain.user.UserEntity;
import com.javaSchool.FinalTask3.domain.user.UserRepository;
import com.javaSchool.FinalTask3.exception.InsufficientPermissions;
import com.javaSchool.FinalTask3.exception.UserDoesNotExist;
import com.javaSchool.FinalTask3.security.JwtUtil;
import com.javaSchool.FinalTask3.utils.StringValues;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

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
    private final UserRepository userRepository;

    /**
     * All arguments constructor.
     * @param repository      {@link OrderRepository} of the {@link OrderEntity}.
     * @param modelMapper     ModelMapper that converts the {@link OrderEntity} instance to {@link OrderDTO}
     * @param userRepository  {@link UserRepository} of the {@link UserEntity}.
     */
    public OrderServiceImpl(OrderRepository repository, ModelMapper modelMapper, UserRepository userRepository) {
        super(repository, modelMapper);
        this.userRepository = userRepository;
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
    public Page<OrderDTO> getAllInstances(OrderRequest orderRequest) {
        // Variables
        final QOrderEntity qOrder = QOrderEntity.orderEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();
        int currentUserId = JwtUtil.getIdFromToken(RequestContextHolder.getRequestAttributes());

        // Get the user that sends the request from the database
        UserEntity currentUser = userRepository.findById(currentUserId).orElseThrow(() ->
                new UserDoesNotExist(String.format(StringValues.INSTANCE_NOT_FOUND, currentUserId))
        );

        // Check if the current user is active
        if(currentUser.isActive()){
            // Check which parameters are present and build a query
            if(!orderRequest.getDeliveryMethod().isEmpty()){
                queryBuilder.and(qOrder.deliveryMethod.name.containsIgnoreCase(orderRequest.getDeliveryMethod()));
            }

            if(!orderRequest.getOrderStatus().isEmpty()){
                queryBuilder.and(qOrder.orderStatus.name.containsIgnoreCase(orderRequest.getOrderStatus()));
            }

            if(!orderRequest.getPaymentMethod().isEmpty()){
                queryBuilder.and(qOrder.paymentMethod.name.containsIgnoreCase(orderRequest.getPaymentMethod()));
            }

            if(!orderRequest.getPaymentStatus().isEmpty()){
                queryBuilder.and(qOrder.paymentStatus.name.containsIgnoreCase(orderRequest.getPaymentStatus()));
            }

            if(orderRequest.getDate() != null){
                queryBuilder.and(qOrder.date.eq(orderRequest.getDate()));
            }

            if(currentUser.isClient()){
                queryBuilder.and(qOrder.user.eq(currentUser));
            } else {
                if(!orderRequest.getName().isEmpty()){
                    queryBuilder.and(qOrder.user.name.containsIgnoreCase(orderRequest.getName())
                    .or(qOrder.user.surname.containsIgnoreCase(orderRequest.getName()))
                    .or(qOrder.user.id.eq(Integer.parseInt(orderRequest.getName()))));
                }
            }

            // Generate the page request
            PageRequest pageRequest = PageRequest.of(
                    orderRequest.getPage(),
                    orderRequest.getSize(),
                    Sort.Direction.valueOf(orderRequest.getSortType()),
                    orderRequest.getSortProperty());

            // Find the data in the repository
            Page<OrderEntity> pageEntities = this.repository.findAll(queryBuilder, pageRequest);

            // Convert the oder page to a orderDTO page
            return pageEntities.map(order -> modelMapper.map(order, this.getDTOClass()));
        } else {
            throw new InsufficientPermissions();
        }
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

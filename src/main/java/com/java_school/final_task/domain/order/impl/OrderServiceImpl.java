package com.java_school.final_task.domain.order.impl;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.BookRepository;
import com.java_school.final_task.domain.order.*;
import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.orderBook.QOrderBookEntity;
import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.exception.book.ProductNotAvailableException;
import com.java_school.final_task.exception.book.ProductOutOfStockException;
import com.java_school.final_task.exception.user.InsufficientPermissionsException;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import com.java_school.final_task.security.JwtUtil;
import com.java_school.final_task.utils.StringValues;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Service class responsible for the interaction between the {@link OrderRepository} and the {@link OrderRestControllerImpl}.
 * Obtains data from the {@link OrderRepository} and returns the object(s) of the {@link OrderEntity} as {@link OrderDTO}
 * to the {@link OrderRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "EMPLOYEE", "CLIENT"})
@Service
public class OrderServiceImpl
        extends AbstractServiceImpl<OrderRepository, OrderEntity, OrderDTO, Integer>
        implements OrderService {
    // Fields
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * All arguments constructor.
     *
     * @param repository     {@link OrderRepository} of the {@link OrderEntity}.
     * @param modelMapper    ModelMapper that converts the {@link OrderEntity} instance to {@link OrderDTO}
     * @param userRepository {@link UserRepository} of the {@link UserEntity}.
     * @param bookRepository {@link BookRepository} of the {@link BookEntity}.
     */
    public OrderServiceImpl(OrderRepository repository, ModelMapper modelMapper, UserRepository userRepository,
                            BookRepository bookRepository) {
        super(repository, modelMapper);
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
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
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    public BigDecimal calculateTotalRevenue(LocalDate startDate, LocalDate endDate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.entityManager);

        QOrderEntity qOrder = QOrderEntity.orderEntity;
        QOrderBookEntity qOrderBook = QOrderBookEntity.orderBookEntity;

        try {
            return BigDecimal.valueOf(queryFactory
                    .select(qOrderBook.amount.multiply(qOrderBook.book.price).sum())
                    .from(qOrderBook)
                    .join(qOrderBook.order, qOrder)
                    .where(
                            qOrder.date.between(startDate, endDate)
                    )
                    .fetchOne());
        } catch (NullPointerException e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    @Secured({"ROLE_ADMIN", "EMPLOYEE"})
    public void deleteInstance(Integer integer) {
        super.deleteInstance(integer);
    }

    @Override
    public Page<OrderDTO> getAllInstances(OrderRequest orderRequest) {
        // Variables
        final QOrderEntity qOrder = QOrderEntity.orderEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();
        int currentUserId = JwtUtil.getIdFromToken(RequestContextHolder.getRequestAttributes());

        // Get the user that sends the request from the database
        UserEntity currentUser = userRepository.findById(currentUserId).orElseThrow(() ->
                new UserDoesNotExistException(String.format(StringValues.USER_DOES_NOT_EXIST, currentUserId))
        );

        // Check if the current user is active
        if (currentUser.isActive()) {
            // Check which parameters are present and build a query
            if (!orderRequest.getDeliveryMethod().isEmpty()) {
                queryBuilder.and(qOrder.deliveryMethod.name.containsIgnoreCase(orderRequest.getDeliveryMethod()));
            }

            if (!orderRequest.getOrderStatus().isEmpty()) {
                queryBuilder.and(qOrder.orderStatus.name.containsIgnoreCase(orderRequest.getOrderStatus()));
            }

            if (!orderRequest.getPaymentMethod().isEmpty()) {
                queryBuilder.and(qOrder.paymentMethod.name.containsIgnoreCase(orderRequest.getPaymentMethod()));
            }

            if (!orderRequest.getPaymentStatus().isEmpty()) {
                queryBuilder.and(qOrder.paymentStatus.name.containsIgnoreCase(orderRequest.getPaymentStatus()));
            }

            if (orderRequest.getDate() != null) {
                queryBuilder.and(qOrder.date.eq(orderRequest.getDate()));
            }

            if (currentUser.isClient()) {
                queryBuilder.and(qOrder.user.eq(currentUser));
            } else {
                if (!orderRequest.getName().isEmpty()) {
                    queryBuilder.and(qOrder.user.name.containsIgnoreCase(orderRequest.getName())
                            .or(qOrder.user.surname.containsIgnoreCase(orderRequest.getName()))
                            .or(qOrder.user.email.containsIgnoreCase(orderRequest.getName())));
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

            // Convert the page to a DTO page
            return pageEntities.map(order -> modelMapper.map(order, this.getDTOClass()));
        } else {
            // TODO Return this to frontend
            throw new InsufficientPermissionsException();
        }
    }

    @Override
    @Transactional
    public OrderDTO saveInstance(SaveOrderDTO saveOrderDTO) {
        // Build an order
        OrderEntity newOrder = OrderEntity.builder()
                .id(saveOrderDTO.getOrder().getId())
                .date(saveOrderDTO.getOrder().getDate())
                .orderStatus(saveOrderDTO.getOrder().getOrderStatus())
                .paymentStatus(saveOrderDTO.getOrder().getPaymentStatus())
                .paymentMethod(saveOrderDTO.getOrder().getPaymentMethod())
                .deliveryMethod(saveOrderDTO.getOrder().getDeliveryMethod())
                .user(saveOrderDTO.getOrder().getUser())
                .orderedBooks(saveOrderDTO.getOrderedBooks())
                .build();

        // Check if the order has an id
        // If it has no id, it's a new order and therefore the stock remains unchanged
        if (newOrder.getId() == 0) {
            newOrder.getOrderedBooks().stream().forEach(orderBook -> {
                BookEntity book = bookRepository.findById(orderBook.getBook().getId()).orElseThrow();

                if (book.isActive()) {
                    int previousAmount = book.getStock();

                    // Check if the product stock is enough to be sold in the issued amount
                    if (previousAmount - orderBook.getAmount() >= 0) {
                        book.setStock(previousAmount - orderBook.getAmount());
                        bookRepository.save(book);
                    } else {
                        throw new ProductOutOfStockException(orderBook.getBook());
                    }
                } else {
                    throw new ProductNotAvailableException(orderBook.getBook());
                }
            });
        }

        // Set the order of the books
        saveOrderDTO.getOrderedBooks().forEach(orderedBook ->
                orderedBook.setOrder(newOrder)
        );

        return modelMapper.map(repository.save(newOrder), getDTOClass());
    }
}

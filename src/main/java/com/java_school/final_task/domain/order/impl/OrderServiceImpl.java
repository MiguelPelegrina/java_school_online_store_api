package com.java_school.final_task.domain.order.impl;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.BookRepository;
import com.java_school.final_task.domain.order.*;
import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.dto.OrderRequestDTO;
import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.order_book.QOrderBookEntity;
import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserService;
import com.java_school.final_task.exception.book.ProductNotAvailableException;
import com.java_school.final_task.exception.book.ProductOutOfStockException;
import com.java_school.final_task.exception.user.InactiveUserException;
import com.java_school.final_task.utils.MailService;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Service class responsible for the interaction between the {@link OrderRepository} and the {@link OrderRestController}.
 * Obtains data from the {@link OrderRepository} and returns the object(s) of the {@link OrderEntity} as {@link OrderDTO}
 * to the {@link OrderRestController}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_CLIENT"})
@Service
public class OrderServiceImpl
        extends AbstractServiceImpl<OrderRepository, OrderEntity, OrderDTO, Integer>
        implements OrderService {
    // Fields
    private final UserService userService;
    private final MailService mailService;
    private final BookRepository bookRepository;
    private final JPAQueryFactory queryFactory;

    /**
     * All arguments constructor.
     *
     * @param repository     {@link OrderRepository} of the {@link OrderEntity}.
     * @param modelMapper    ModelMapper that converts the {@link OrderEntity} instance to {@link OrderDTO}
     * @param userService    {@link UserService} of the {@link UserEntity}.
     * @param mailService    {@link MailService} of the {@link MailService}
     * @param bookRepository {@link BookRepository} of the {@link BookEntity}.
     * @param queryFactory   {@link JPAQueryFactory} for query and DML clause creation.
     */
    public OrderServiceImpl(OrderRepository repository, ModelMapper modelMapper, MailService mailService,
                            UserService userService, BookRepository bookRepository, JPAQueryFactory queryFactory) {
        super(repository, modelMapper);
        this.userService = userService;
        this.mailService = mailService;
        this.bookRepository = bookRepository;
        this.queryFactory = queryFactory;
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
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    public BigDecimal[] calculateRevenueOfYearByMonths(LocalDate date, boolean isLast12Months) {
        BigDecimal[] revenues = new BigDecimal[12];
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate;

        if (isLast12Months) {
            startDate = currentDate.minusMonths(12); // Start date is 12 months before the current date
        } else {
            startDate = YearMonth.of(date.getYear(), 1).atDay(1); // Start date is the first day of the specified year
        }

        for (int i = 0; i < 12; i++) {
            YearMonth yearMonth = YearMonth.from(startDate.plusMonths(i + 1));
            LocalDate firstOfMonth = yearMonth.atDay(1);
            LocalDate lastOfMonth = yearMonth.atEndOfMonth();

            // Ensure we don't go beyond the current date for last 12 months
            if (isLast12Months && lastOfMonth.isAfter(currentDate)) {
                lastOfMonth = currentDate;
            }
            revenues[i] = this.calculateTotalRevenue(firstOfMonth, lastOfMonth);
        }
        return revenues;
    }

    @Override
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    public void deleteInstance(Integer integer) {
        super.deleteInstance(integer);
    }

    @Override
    public Page<OrderDTO> getAllInstances(OrderRequestDTO orderRequestDTO) {
        // Variables
        final QOrderEntity qOrder = QOrderEntity.orderEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Get the user that sends the request from the database
        UserEntity currentUser = userService.getCurrentUser();

        // Check if the current user is active
        if (currentUser.isActive()) {
            handleQueryParameters(orderRequestDTO, queryBuilder, qOrder);

            if (orderRequestDTO.getDate() != null) {
                queryBuilder.and(qOrder.date.eq(orderRequestDTO.getDate()));
            }

            if (currentUser.isClient()) {
                queryBuilder.and(qOrder.user.eq(currentUser));
            } else {
                if (!orderRequestDTO.getName().isEmpty()) {
                    queryBuilder.and(qOrder.user.name.containsIgnoreCase(orderRequestDTO.getName())
                            .or(qOrder.user.surname.containsIgnoreCase(orderRequestDTO.getName()))
                            .or(qOrder.user.email.containsIgnoreCase(orderRequestDTO.getName())));
                }
            }

            // Generate the page request
            PageRequest pageRequest = PageRequest.of(
                    orderRequestDTO.getPage(),
                    orderRequestDTO.getSize(),
                    Sort.Direction.valueOf(orderRequestDTO.getSortType()),
                    orderRequestDTO.getSortProperty());

            // Find the data in the repository
            Page<OrderEntity> pageEntities = this.repository.findAll(queryBuilder, pageRequest);

            // Convert the page to a DTO page
            return pageEntities.map(order -> modelMapper.map(order, this.getDTOClass()));
        } else {
            throw new InactiveUserException();
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
                        throw new ProductOutOfStockException(orderBook.getBook().getTitle());
                    }
                } else {
                    throw new ProductNotAvailableException(orderBook.getBook().getTitle());
                }
            });
        }

        // Set the order of the books
        saveOrderDTO.getOrderedBooks().forEach(orderedBook ->
                orderedBook.setOrder(newOrder)
        );

        this.mailService.sendOrderConfirmationMail(saveOrderDTO);

        return modelMapper.map(repository.save(newOrder), getDTOClass());
    }

    /**
     * Handles a query parameter by adding a condition to the BooleanBuilder.
     *
     * @param parameter    The query parameter to handle.
     * @param queryBuilder The BooleanBuilder to which the condition will be added.
     * @param expression   The StringExpression representing the field to filter on.
     */
    private void handleQueryParameter(String parameter, BooleanBuilder queryBuilder, StringExpression expression) {
        queryBuilder.and(!parameter.trim().isEmpty() ? expression.containsIgnoreCase(parameter) : null);
    }

    /**
     * Handles multiple query parameters for an order request by delegating to handleQueryParameter for each parameter.
     *
     * @param orderRequestDTO The OrderRequestDTO containing the query parameters.
     * @param queryBuilder    The BooleanBuilder to which the conditions will be added.
     * @param qOrder          The QOrderEntity representing the query entity.
     */
    private void handleQueryParameters(OrderRequestDTO orderRequestDTO, BooleanBuilder queryBuilder, QOrderEntity qOrder) {
        handleQueryParameter(orderRequestDTO.getDeliveryMethod(), queryBuilder, qOrder.deliveryMethod.name);
        handleQueryParameter(orderRequestDTO.getOrderStatus(), queryBuilder, qOrder.orderStatus.name);
        handleQueryParameter(orderRequestDTO.getPaymentMethod(), queryBuilder, qOrder.paymentMethod.name);
        handleQueryParameter(orderRequestDTO.getPaymentStatus(), queryBuilder, qOrder.paymentStatus.name);
    }
}

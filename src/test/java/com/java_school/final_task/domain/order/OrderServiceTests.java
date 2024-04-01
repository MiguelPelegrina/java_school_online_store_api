package com.java_school.final_task.domain.order;

import com.java_school.final_task.domain.book.BookRepository;
import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.dto.OrderRequestDTO;
import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.order.impl.OrderServiceImpl;
import com.java_school.final_task.domain.order_book.OrderBookRepository;
import com.java_school.final_task.domain.user.impl.UserServiceImpl;
import com.java_school.final_task.exception.book.ProductNotAvailableException;
import com.java_school.final_task.exception.book.ProductOutOfStockException;
import com.java_school.final_task.exception.user.InactiveUserException;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import com.java_school.final_task.security.JwtUtil;
import com.java_school.final_task.utils.impl.MailServiceImpl;
import com.querydsl.core.BooleanBuilder;
import mothers.order.OrderMother;
import mothers.order_book.OrderBookMother;
import mothers.request.RequestMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link OrderServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceTests {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderBookRepository orderBookRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private MailServiceImpl mailService;

    @InjectMocks
    private OrderServiceImpl service;

    @Mock
    private ModelMapper modelMapper;

    private SaveOrderDTO instance;

    private OrderDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        instance = createSaveOrderDTO();

        instanceDTO = OrderMother.createOrderDTO();
    }

    @Test
    void OrderService_GetEntityId_ReturnsIdClass() {
        // Act
        int entityId = service.getEntityId(instance.getOrder());

        // Assert
        assertEquals(0, entityId);
    }

    @Test
    void OrderService_CreateOrder_ReturnsSavedOrderDTO() {
        // Arrange
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(instance.getOrder());
        when(bookRepository.findById(instance.getOrderedBooks().get(0).getBook().getId()))
                .thenReturn(Optional.ofNullable(OrderBookMother.createOrderBook().getBook()));
        lenient().when(bookRepository.save(instance.getOrderedBooks().get(0).getBook()))
                .thenReturn(OrderBookMother.createOrderBook().getBook());
        when(modelMapper.map(instance.getOrder(), service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        OrderDTO savedInstance = service.saveInstance(instance);

        // Assert
        assertThat(savedInstance).isNotNull();
        verify(orderRepository, times(1)).save(instance.getOrder());
        verify(modelMapper, times(1)).map(instance.getOrder(), OrderDTO.class);
        assertEquals(instanceDTO, savedInstance);
    }

    @Test
    void OrderService_CreateOrder_ThrowsProductOutOfStockException() {
        // Arrange
        instance.getOrderedBooks().get(0).getBook().setStock(0);

        when(bookRepository.findById(instance.getOrderedBooks().get(0).getBook().getId()))
                .thenReturn(Optional.ofNullable(instance.getOrderedBooks().get(0).getBook()));

        // Act
        assertThrows(ProductOutOfStockException.class, () -> service.saveInstance(instance));

        // Assert
        verify(bookRepository, times(instance.getOrderedBooks().size())).findById(anyInt());
    }

    @Test
    void OrderService_GetAllInstances_ThrowsUserDoesNotExistException() {
        // Arrange
        OrderRequestDTO request = RequestMother.createOrderRequest();
        RequestMother.prepareRequestAttributes(instance.getOrder().getUser().getId());

        when(userService.getCurrentUser()).thenThrow(UserDoesNotExistException.class);

        // Act
        assertThrows(UserDoesNotExistException.class, () -> service.getAllInstances(request));

        // Assert
        verify(userService, times(1)).getCurrentUser();
    }

    @Test
    void OrderService_GetAllInstances_ThrowsInactiveUserException() {
        // Arrange
        instance.getOrder().getUser().setActive(false);
        OrderRequestDTO request = RequestMother.createOrderRequest();
        RequestMother.prepareRequestAttributes(instance.getOrder().getUser().getId());

        when(userService.getCurrentUser()).thenReturn(instance.getOrder().getUser());

        // Act
        assertThrows(InactiveUserException.class, () -> service.getAllInstances(request));

        // Assert
        verify(userService, times(1)).getCurrentUser();
    }

    @Test
    void OrderService_CreateOrder_ThrowsProductNotAvailableException() {
        // Arrange
        instance.getOrderedBooks().get(0).getBook().setActive(false);

        when(bookRepository.findById(instance.getOrderedBooks().get(0).getBook().getId()))
                .thenReturn(Optional.ofNullable(instance.getOrderedBooks().get(0).getBook()));

        // Act
        assertThrows(ProductNotAvailableException.class, () -> service.saveInstance(instance));

        // Assert
        verify(bookRepository, times(instance.getOrderedBooks().size())).findById(anyInt());
    }

    @Test
    void OrderService_DeleteOrderById_ReturnsVoid() {
        // Arrange
        lenient().when(orderRepository.findById(instance.getOrder().getId())).thenReturn(Optional.ofNullable(instance.getOrder()));

        // Act
        service.deleteInstance(instance.getOrder().getId());

        // Assert
        verify(orderRepository).deleteById(instance.getOrder().getId());
    }

    @Test
    void OrderService_GetAllOrdersByParams_ReturnsOrderDTOPage() {
        // Arrange
        final QOrderEntity qInstance = QOrderEntity.orderEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        List<OrderEntity> instances = new ArrayList<>();
        instances.add(instance.getOrder());

        Page<OrderEntity> page = new PageImpl<>(instances);

        queryBuilder.and(qInstance.deliveryMethod.name.containsIgnoreCase(instance.getOrder().getDeliveryMethod().getName()));
        queryBuilder.and(qInstance.orderStatus.name.containsIgnoreCase(instance.getOrder().getOrderStatus().getName()));
        queryBuilder.and(qInstance.paymentMethod.name.containsIgnoreCase(instance.getOrder().getPaymentMethod().getName()));
        queryBuilder.and(qInstance.paymentStatus.name.containsIgnoreCase(instance.getOrder().getPaymentStatus().getName()));
        queryBuilder.and(qInstance.date.eq(instance.getOrder().getDate()));

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setDeliveryMethod("DeliveryMethod");
        orderRequestDTO.setOrderStatus("OrderStatus");
        orderRequestDTO.setPaymentMethod("PaymentMethod");
        orderRequestDTO.setPaymentStatus("PaymentStatus");
        orderRequestDTO.setDate(LocalDate.now());
        orderRequestDTO.setPage(0);
        orderRequestDTO.setSize(10);
        orderRequestDTO.setSortType("ASC");
        orderRequestDTO.setSortProperty("title");

        PageRequest pageRequest = PageRequest.of(
                orderRequestDTO.getPage(),
                orderRequestDTO.getSize(),
                Sort.Direction.valueOf(orderRequestDTO.getSortType()),
                orderRequestDTO.getSortProperty());

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWd1ZWxAZW1haWwuY29tIiwiaWQiOjQsInJvbGVzIjpbIkFETUlOIl0sImV4cCI6MTkxNjY3Mzk1NX0.wrX_u_broIqIiO-47Z5pZ4hI8zvA40Yj40nAdBCpFJM");

        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);

        when(userService.getCurrentUser()).thenReturn(instance.getOrder().getUser());

        try (MockedStatic<JwtUtil> mockedStatic = mockStatic(JwtUtil.class)) {
            mockedStatic.when(() -> JwtUtil.getIdFromToken(requestAttributes)).thenReturn(instance.getOrder().getUser().getId());
        }

        when(orderRepository.findAll(queryBuilder, pageRequest)).thenReturn(page);
        when(modelMapper.map(instance.getOrder(), service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        Page<OrderDTO> resultDTOs = service.getAllInstances(orderRequestDTO);

        // Assert
        verify(orderRepository, times(1)).findAll(queryBuilder, pageRequest);
        verify(modelMapper, times(1)).map(instance.getOrder(), service.getDTOClass());
        assertThat(resultDTOs).isNotNull().hasSize(1);
        assertThat(resultDTOs.getContent().get(0)).isEqualTo(instanceDTO);
    }

    // Auxiliary methods
    private SaveOrderDTO createSaveOrderDTO() {
        return SaveOrderDTO.builder()
                .order(OrderMother.createOrder())
                .orderedBooks(List.of(OrderBookMother.createOrderBook()))
                .build();
    }
}

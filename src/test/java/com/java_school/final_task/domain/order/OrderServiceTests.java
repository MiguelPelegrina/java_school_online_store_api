package com.java_school.final_task.domain.order;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.BookRepository;
import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.book.genre.BookGenreEntity;
import com.java_school.final_task.domain.book.parameter.BookParameterDTO;
import com.java_school.final_task.domain.book.parameter.BookParameterEntity;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatDTO;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatEntity;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodDTO;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.order.impl.OrderServiceImpl;
import com.java_school.final_task.domain.order.orderStatus.OrderStatusDTO;
import com.java_school.final_task.domain.order.orderStatus.OrderStatusEntity;
import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodDTO;
import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodEntity;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusDTO;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusEntity;
import com.java_school.final_task.domain.orderBook.OrderBookEntity;
import com.java_school.final_task.domain.orderBook.OrderBookJsonDTO;
import com.java_school.final_task.domain.orderBook.OrderBookRepository;
import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.role.RoleEntity;
import com.java_school.final_task.domain.user.UserDTO;
import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.domain.user.userAddress.UserAddressDTO;
import com.java_school.final_task.domain.user.userAddress.UserAddressEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.java_school.final_task.domain.userRole.UserRoleEntity;
import com.java_school.final_task.domain.userRole.dto.UserRoleJsonDTO;
import com.java_school.final_task.exception.book.ProductNotAvailableException;
import com.java_school.final_task.exception.book.ProductOutOfStockException;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import com.java_school.final_task.security.JwtUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link OrderServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderBookRepository orderBookRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderServiceImpl service;

    @Mock
    private ModelMapper modelMapper;

    private SaveOrderDTO instance;

    private OrderDTO instanceDTO;

    @BeforeEach
    public void setUp(){
        instance = createSaveOrderDTO();

        instanceDTO = createOrderDTO();
    }

    @Test
    public void OrderService_GetEntityId_ReturnsIdClass(){
        // Act
        int entityId = service.getEntityId(instance.getOrder());

        // Assert
        assertEquals(0, entityId);
    }

    @Test
    public void OrderService_CreateOrder_ReturnsSavedOrderDTO(){
        // Arrange
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(instance.getOrder());
        when(bookRepository.findById(instance.getOrderedBooks().get(0).getBook().getId())).thenReturn(Optional.ofNullable(createOrderBook().getBook()));
        lenient().when(bookRepository.save(instance.getOrderedBooks().get(0).getBook())).thenReturn(createOrderBook().getBook());
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
    public void OrderService_CreateOrder_ThrowsProductOutOfStockException(){
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
    public void OrderService_CreateOrder_ThrowsProductNotAvailableException(){
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
    public void OrderService_DeleteOrderById_ReturnsVoid(){
        // Arrange
        lenient().when(orderRepository.findById(instance.getOrder().getId())).thenReturn(Optional.ofNullable(instance.getOrder()));

        // Act
        service.deleteInstance(instance.getOrder().getId());

        // Assert
        verify(orderRepository).deleteById(instance.getOrder().getId());
    }

    // TODO I might have went over board
    @Test
    public void OrderService_GetAllOrdersByParams_ReturnsOrderDTOPage(){
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

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setDeliveryMethod("DeliveryMethod");
        orderRequest.setOrderStatus("OrderStatus");
        orderRequest.setPaymentMethod("PaymentMethod");
        orderRequest.setPaymentStatus("PaymentStatus");
        orderRequest.setDate(LocalDate.now());
        orderRequest.setPage(0);
        orderRequest.setSize(10);
        orderRequest.setSortType("ASC");
        orderRequest.setSortProperty("title");

        PageRequest pageRequest = PageRequest.of(
                orderRequest.getPage(),
                orderRequest.getSize(),
                Sort.Direction.valueOf(orderRequest.getSortType()),
                orderRequest.getSortProperty());

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWd1ZWxAZW1haWwuY29tIiwiaWQiOjQsInJvbGVzIjpbIkFETUlOIl0sImV4cCI6MTkxNjY3Mzk1NX0.wrX_u_broIqIiO-47Z5pZ4hI8zvA40Yj40nAdBCpFJM");

        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);

        when(userRepository.findById(instance.getOrder().getUser().getId())).thenReturn(Optional.ofNullable(instance.getOrder().getUser()));

        try (MockedStatic<JwtUtil> mockedStatic = mockStatic(JwtUtil.class)){
            mockedStatic.when(() -> JwtUtil.getIdFromToken(requestAttributes)).thenReturn(instance.getOrder().getUser().getId());
        }

        when(orderRepository.findAll(queryBuilder, pageRequest)).thenReturn(page);
        when(modelMapper.map(instance.getOrder(), service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        Page<OrderDTO> resultDTOs = service.getAllInstances(orderRequest);

        // Assert
        verify(orderRepository, times(1)).findAll(queryBuilder, pageRequest);
        verify(modelMapper, times(1)).map(instance.getOrder(), service.getDTOClass());
        assertThat(resultDTOs).isNotNull();
        assertThat(resultDTOs).hasSize(1);
        assertThat(resultDTOs.getContent().get(0)).isEqualTo(instanceDTO);
    }

    @Test
    public void OrderService_GetCalculatedRevenue_ReturnsBigDecimal(){

    }

    // Auxiliary methods
    private OrderEntity createOrder() {
        return OrderEntity.builder()
                .id(0)
                .orderedBooks(null)
                .date(LocalDate.now())
                .deliveryMethod(DeliveryMethodEntity.builder().name("DeliveryMethod").isActive(true).build())
                .orderStatus(OrderStatusEntity.builder().name("OrderStatus").isActive(true).build())
                .paymentMethod(PaymentMethodEntity.builder().name("PaymentMethod").isActive(true).build())
                .paymentStatus(PaymentStatusEntity.builder().name("PaymentStatus").isActive(true).build())
                .orderedBooks(List.of(createOrderBook()))
                .user(UserEntity.builder()
                        .id(4)
                        .active(true)
                        .email("email@.com")
                        .dateOfBirth(LocalDate.now())
                        .phone("12345678912")
                        .password("Password")
                        .name("Name")
                        .surname("Surname")
                        .roles(Set.of(UserRoleEntity.builder()
                                        .assignedDate(LocalDate.now())
                                        .id(1)
                                        .role(RoleEntity.builder()
                                                .name("ADMIN")
                                                .build())
                                .build()))
                        .address(UserAddressEntity.builder()
                                .postalCode(PostalCodeEntity.builder()
                                        .isActive(true)
                                        .code("Code")
                                        .city(CityEntity.builder()
                                                .name("City")
                                                .countryName(CountryEntity.builder()
                                                        .isActive(true)
                                                        .name("Country")
                                                        .build())
                                                .isActive(true)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
    }

    private OrderBookEntity createOrderBook(){
        return OrderBookEntity.builder()
                .id(1)
                .book(BookEntity.builder()
                        .title("Title")
                        .active(true)
                        .genre(new BookGenreEntity("Genre"))
                        .image("Image")
                        .isbn("ISBN")
                        .price(new BigDecimal("1.23"))
                        .stock(10)
                        .parameters(
                                BookParameterEntity.builder()
                                        .author("Author")
                                        .format(new BookParametersFormatEntity("Format"))
                                        .isActive(true)
                                        .build()
                        )
                        .build())
                .amount(1)
                .build();
    }

    private OrderBookJsonDTO createOrderBookJsonDTO(){
        return OrderBookJsonDTO.builder()
                .id(1)
                .book(BookDTO.builder()
                        .title("Title")
                        .active(true)
                        .genre(new BookGenreDTO("Genre"))
                        .image("Image")
                        .isbn("ISBN")
                        .price(new BigDecimal("1.23"))
                        .stock(9)
                        .parameters(
                                BookParameterDTO.builder()
                                        .author("Author")
                                        .format(new BookParametersFormatDTO("Format"))
                                        .isActive(true)
                                        .build()
                        )
                        .build())
                .amount(1)
                .build();
    }

    private UserDTO createUserDTO() {
        return UserDTO.builder()
                .id(4)
                .isActive(true)
                .email("email@.com")
                .dateOfBirth(LocalDate.now())
                .phone("12345678912")
                .name("Name")
                .surname("Surname")
                .roles(Set.of(UserRoleJsonDTO.builder()
                                .role(RoleDTO.builder()
                                        .name("ADMIN")
                                        .build())
                                .assignedDate(LocalDate.now())
                        .build()))
                .address(UserAddressDTO.builder()
                        .postalCode(PostalCodeDTO.builder()
                                .isActive(true)
                                .code("Code")
                                .city(CityDTO.builder()
                                        .name("City")
                                        .country(CountryDTO.builder()
                                                .isActive(true)
                                                .name("Country")
                                                .build())
                                        .isActive(true)
                                        .build())
                                .build())
                        .build())
                .build();
    }

    private OrderDTO createOrderDTO() {
        return OrderDTO.builder()
                .id(0)
                .user(createUserDTO())
                .date(LocalDate.now())
                .deliveryMethod(DeliveryMethodDTO.builder().name("DeliveryMethod").isActive(true).build())
                .orderStatus(OrderStatusDTO.builder().name("OrderStatus").isActive(true).build())
                .paymentMethod(PaymentMethodDTO.builder().name("PaymentMethod").isActive(true).build())
                .paymentStatus(PaymentStatusDTO.builder().name("PaymentStatus").isActive(true).build())
                .orderedBooks(List.of(createOrderBookJsonDTO()))
                .build();
    }

    private SaveOrderDTO createSaveOrderDTO() {
        return SaveOrderDTO.builder()
                .order(createOrder())
                .orderedBooks(List.of(createOrderBook()))
                .build();
    }
}

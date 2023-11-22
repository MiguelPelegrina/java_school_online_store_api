package com.java_school.final_task.domain.order_book;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.book.genre.BookGenreEntity;
import com.java_school.final_task.domain.book.parameter.BookParameterDTO;
import com.java_school.final_task.domain.book.parameter.BookParameterEntity;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatDTO;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatEntity;
import com.java_school.final_task.domain.order.OrderEntity;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.java_school.final_task.domain.order.orderStatus.OrderStatusEntity;
import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodEntity;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusEntity;
import com.java_school.final_task.domain.orderBook.OrderBookDTO;
import com.java_school.final_task.domain.orderBook.OrderBookEntity;
import com.java_school.final_task.domain.orderBook.OrderBookRepository;
import com.java_school.final_task.domain.orderBook.impl.OrderBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link OrderBookServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class OrderBookServiceTests {
    // Fields
    @Mock
    private OrderBookRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderBookServiceImpl service;

    private OrderBookEntity instance;
    private OrderBookDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        instance = OrderBookEntity.builder()
                .order(OrderEntity.builder()
                        .id(1)
                        .date(LocalDate.now())
                        .deliveryMethod(DeliveryMethodEntity.builder().name("DeliveryMethod").isActive(true).build())
                        .orderStatus(OrderStatusEntity.builder().name("OrderStatus").isActive(true).build())
                        .paymentMethod(PaymentMethodEntity.builder().name("PaymentMethod").isActive(true).build())
                        .paymentStatus(PaymentStatusEntity.builder().name("PaymentStatus").isActive(true).build())
                        .orderedBooks(List.of(buildOrderBook()))
                        .build())
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
                .id(1)
                .build();

        instanceDTO = OrderBookDTO.builder()
                .book(BookDTO.builder()
                        .title("Title")
                        .active(true)
                        .genre(new BookGenreDTO("Genre"))
                        .image("Image")
                        .isbn("ISBN")
                        .price(new BigDecimal("1.23"))
                        .stock(10)
                        .parameters(
                                BookParameterDTO.builder()
                                        .author("Author")
                                        .format(new BookParametersFormatDTO("Hardcover"))
                                        .isActive(true)
                                        .build()
                        )
                        .build())
                .amount(1)
                .id(1)
                .build();
    }

    @Test
    public void OrderBookService_GetEntityId_ReturnsIdClass(){
        // Arrange
        OrderBookEntity instance = new OrderBookEntity();
        instance.setId(1);

        // Act
        int entityId = service.getEntityId(instance);

        // Assert
        assertEquals(1, entityId);
    }

    // Auxiliary methods
    private OrderBookEntity buildOrderBook(){
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
}

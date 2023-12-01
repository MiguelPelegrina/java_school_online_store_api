package com.java_school.final_task.domain.order_book;

import com.java_school.final_task.domain.order_book.impl.OrderBookServiceImpl;
import mothers.order_book.OrderBookMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link OrderBookServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class OrderBookServiceTests {
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
        instance = OrderBookMother.createOrderBook();

        instanceDTO = OrderBookMother.createOrderBookDTO();
    }

    @Test
    void OrderBookService_GetEntityId_ReturnsIdClass() {
        // Arrange
        OrderBookEntity instance = new OrderBookEntity();
        instance.setId(1);

        // Act
        int entityId = service.getEntityId(instance);

        // Assert
        assertEquals(1, entityId);
    }

    @Test
    void OrderBookService_GetDTOClass_ReturnsDTOClass() {
        // Assert
        assertEquals(service.getDTOClass(), OrderBookDTO.class);
    }
}

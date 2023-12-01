package com.java_school.final_task.domain.order.order_status;

import com.java_school.final_task.domain.order.order_status.impl.OrderStatusServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link OrderStatusServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class OrderStatusServiceTests {
    // Fields
    @Mock
    private OrderStatusRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderStatusServiceImpl service;

    private OrderStatusEntity instance;
    private OrderStatusDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = OrderStatusEntity.builder()
                .isActive(true)
                .name("OrderStatus")
                .build();

        instanceDTO = OrderStatusDTO.builder()
                .isActive(true)
                .name("OrderStatus")
                .build();
    }

    @Test
    void OrderStatusService_GetEntityId_ReturnsIdClass() {
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("OrderStatus", entityId);
    }

    @Test
    void OrderStatusService_GetAllOrderStatusesFiltered_ReturnsOrderStatusDTOs() {
        // Arrange
        List<OrderStatusEntity> instances = new ArrayList<>();
        instances.add(instance);

        final QOrderStatusEntity qInstance = QOrderStatusEntity.orderStatusEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qInstance.isActive.eq(true));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<OrderStatusDTO> resultDTOs = service.getAllInstances(Optional.of(true));

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull().hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }
}

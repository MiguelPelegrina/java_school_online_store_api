package com.java_school.final_task.domain.order.payment_status;

import com.java_school.final_task.domain.order.payment_status.impl.PaymentStatusServiceImpl;
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
 * Test class for {@link PaymentStatusServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class PaymentStatusServiceTests {
    // Fields
    @Mock
    private PaymentStatusRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PaymentStatusServiceImpl service;

    private PaymentStatusEntity instance;
    private PaymentStatusDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = PaymentStatusEntity.builder()
                .isActive(true)
                .name("Payment")
                .build();

        instanceDTO = PaymentStatusDTO.builder()
                .isActive(true)
                .name("Payment")
                .build();
    }

    @Test
    void PaymentStatusService_GetEntityId_ReturnsIdClass() {
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("Payment", entityId);
    }

    @Test
    void PaymentStatusService_GetAllPaymentStatusesFiltered_ReturnsPaymentStatusDTOs() {
        // Arrange
        List<PaymentStatusEntity> instances = new ArrayList<>();
        instances.add(instance);

        final QPaymentStatusEntity qInstance = QPaymentStatusEntity.paymentStatusEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qInstance.isActive.eq(true));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<PaymentStatusDTO> resultDTOs = service.getAllInstances(Optional.of(true));

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull().hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }
}

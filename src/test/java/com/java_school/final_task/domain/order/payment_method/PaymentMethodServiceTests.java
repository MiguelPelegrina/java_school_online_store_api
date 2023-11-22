package com.java_school.final_task.domain.order.payment_method;

import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodDTO;
import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodEntity;
import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodRepository;
import com.java_school.final_task.domain.order.paymentMethod.QPaymentMethodEntity;
import com.java_school.final_task.domain.order.paymentMethod.impl.PaymentMethodServiceImpl;
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
 * Test class for {@link PaymentMethodServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class PaymentMethodServiceTests {
    // Fields
    @Mock
    private PaymentMethodRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PaymentMethodServiceImpl service;

    private PaymentMethodEntity instance;
    private PaymentMethodDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = PaymentMethodEntity.builder()
                .isActive(true)
                .name("Payment")
                .build();

        instanceDTO = PaymentMethodDTO.builder()
                .isActive(true)
                .name("Payment")
                .build();
    }

    @Test
    public void PaymentMethodService_GetEntityId_ReturnsIdClass(){
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("Payment", entityId);
    }

    @Test
    public void PaymentMethodService_GetAllPaymentMethodsFiltered_ReturnsPaymentMethodDTOs(){
        // Arrange
        List<PaymentMethodEntity> instances = new ArrayList<>();
        instances.add(instance);

        final QPaymentMethodEntity qInstance  = QPaymentMethodEntity.paymentMethodEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qInstance.isActive.eq(true));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<PaymentMethodDTO> resultDTOs = service.getAllInstances(Optional.of(true));

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull();
        assertThat(resultDTOs).hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }
}

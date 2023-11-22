package com.java_school.final_task.domain.order.delivery_method;

import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodDTO;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodRepository;
import com.java_school.final_task.domain.order.deliveryMethod.QDeliveryMethodEntity;
import com.java_school.final_task.domain.order.deliveryMethod.impl.DeliveryMethodServiceImpl;
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
 * Test class for {@link DeliveryMethodServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class DeliveryMethodServiceTests {
    // Fields
    @Mock
    private DeliveryMethodRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DeliveryMethodServiceImpl service;

    private DeliveryMethodEntity instance;
    private DeliveryMethodDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = DeliveryMethodEntity.builder()
                .isActive(true)
                .name("Delivery")
                .build();

        instanceDTO = DeliveryMethodDTO.builder()
                .isActive(true)
                .name("Delivery")
                .build();
    }

    @Test
    public void DeliveryMethodService_GetEntityId_ReturnsIdClass(){
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("Delivery", entityId);
    }

    @Test
    public void DeliveryMethodService_GetAllDeliveryMethodsFiltered_ReturnsDeliveryMethodDTOs(){
        // Arrange
        List<DeliveryMethodEntity> instances = new ArrayList<>();
        instances.add(instance);

        final QDeliveryMethodEntity qInstance  = QDeliveryMethodEntity.deliveryMethodEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qInstance.isActive.eq(true));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<DeliveryMethodDTO> resultDTOs = service.getAllInstances(Optional.of(true));

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull();
        assertThat(resultDTOs).hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }
}

package com.java_school.final_task.domain.order.delivery_method;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodDTO;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.java_school.final_task.domain.order.deliveryMethod.impl.DeliveryMethodRestControllerImpl;
import com.java_school.final_task.domain.order.deliveryMethod.impl.DeliveryMethodServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link DeliveryMethodRestControllerImpl}.
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DeliveryMethodRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeliveryMethodServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void DeliveryMethodController_GetAllDeliveryMethodsByParams_ReturnDeliveryMethodDTOs() throws Exception {
        // Arrange
        List<DeliveryMethodDTO> instances = Arrays.asList(instanceDTO);
        when(service.getAllInstances(any())).thenReturn(instances);

        // Act
        ResultActions result = mockMvc.perform(request(HttpMethod.GET,"/delivery_methods/search")
                .param("active", String.valueOf(instance.isActive())));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(instances.size())))
                .andExpect(jsonPath("$[0].active", is(instanceDTO.isActive())));
    }
}

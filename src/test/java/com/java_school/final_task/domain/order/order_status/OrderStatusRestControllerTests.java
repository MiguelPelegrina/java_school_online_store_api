package com.java_school.final_task.domain.order.order_status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.order.order_status.impl.OrderStatusRestControllerImpl;
import com.java_school.final_task.domain.order.order_status.impl.OrderStatusServiceImpl;
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
 * Test class for {@link OrderStatusRestControllerImpl}.
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderStatusRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderStatusServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderStatusEntity instance;
    private OrderStatusDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = OrderStatusEntity.builder()
                .isActive(true)
                .name("Delivery")
                .build();

        instanceDTO = OrderStatusDTO.builder()
                .isActive(true)
                .name("Delivery")
                .build();
    }

    @Test
    void OrderStatusController_GetAllOrderStatusesByParams_ReturnOrderStatusDTOs() throws Exception {
        // Arrange
        List<OrderStatusDTO> instances = Arrays.asList(instanceDTO);
        when(service.getAllInstances(any())).thenReturn(instances);

        // Act
        ResultActions result = mockMvc.perform(request(HttpMethod.GET, "/order_statuses/search")
                .param("active", String.valueOf(instance.isActive())));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(instances.size())))
                .andExpect(jsonPath("$[0].active", is(instanceDTO.isActive())));
    }
}

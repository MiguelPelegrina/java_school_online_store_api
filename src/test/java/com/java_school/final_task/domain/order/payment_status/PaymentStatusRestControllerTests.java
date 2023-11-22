package com.java_school.final_task.domain.order.payment_status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusDTO;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusEntity;
import com.java_school.final_task.domain.order.paymentStatus.impl.PaymentStatusRestControllerImpl;
import com.java_school.final_task.domain.order.paymentStatus.impl.PaymentStatusServiceImpl;
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
 * Test class for {@link PaymentStatusRestControllerImpl}.
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PaymentStatusRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentStatusServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void PaymentStatusController_GetAllPaymentStatusesByParams_ReturnPaymentStatusDTOs() throws Exception {
        // Arrange
        List<PaymentStatusDTO> instances = Arrays.asList(instanceDTO);
        when(service.getAllInstances(any())).thenReturn(instances);

        // Act
        ResultActions result = mockMvc.perform(request(HttpMethod.GET,"/payment_statuses/search")
                .param("active", String.valueOf(instance.isActive())));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(instances.size())))
                .andExpect(jsonPath("$[0].active", is(instanceDTO.isActive())));
    }
}

package com.java_school.final_task.domain.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.impl.OrderRestControllerImpl;
import com.java_school.final_task.domain.order.impl.OrderServiceImpl;
import com.java_school.final_task.domain.user.UserRepository;
import mothers.order.OrderMother;
import mothers.request.RequestMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link OrderRestControllerImpl}
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserRepository repository;

    private OrderDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instanceDTO = OrderMother.createOrderDTO();
    }

    @Test
    void OrderRestController_GetAllOrdersByParams_ReturnOrderDTOPage() throws Exception {
        // Arrange
        Page<OrderDTO> page = new PageImpl<>(Collections.singletonList(instanceDTO));

        OrderRequest request = RequestMother.createOrderRequest();

        when(service.getAllInstances(request)).thenReturn(page);

        // Act
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/orders/search")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "Name")
                .param("date", String.valueOf(LocalDate.now()))
                .param("deliveryMethod", "")
                .param("paymentMethod", "")
                .param("orderStatus", "")
                .param("paymentStatus", "")
                .param("page", "0")
                .param("size", "10")
                .param("sortType", "ASC")
                .param("sortProperty", "name"));

        // Assert
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.totalElements").value(page.getTotalElements()))
                .andExpect(jsonPath("$.content[0].user.name").value("Name"));
    }

    @Test
    void OrderRestController_CreateOrder_ReturnNoContent() throws Exception {
        // Arrange
        OrderEntity instance = OrderMother.createOrder();
        when(service.saveInstance(instance)).thenReturn(null);

        // Act
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/orders/withBooks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instance))
        );

        // Assert
        result.andExpect(status().isNoContent());
    }

    /*@Test
    void OrderController_CreateOrder_ReturnCreated() throws Exception {
        // Arrange
        SaveOrderDTO instance = SaveOrderDTO.builder()
                .order(OrderMother.createOrder())
                .orderedBooks(List.of(OrderBookMother.createOrderBook()))
                .build();
        when(service.saveInstance(instance)).thenReturn(instanceDTO);

        // Act
        ResultActions result = mockMvc.perform(post("/orders/withBooks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instanceDTO)));

        // Assert
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is(instanceDTO.getId())));
    }*/
}

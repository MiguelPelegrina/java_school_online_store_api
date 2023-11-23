package com.java_school.final_task.domain.order;

import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.impl.OrderRestControllerImpl;
import com.java_school.final_task.domain.order.impl.OrderServiceImpl;
import com.java_school.final_task.mothers.order.OrderMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Test class for {@link OrderRestControllerImpl}
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OrderRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceImpl service;

    private OrderDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instanceDTO = OrderMother.createOrderDTO();
    }

    @Test
    public void OrderRestController_GetAllOrdersByParams_ReturnOrderDTOPage() throws Exception {
        // Arrange
        Page<OrderDTO> page = new PageImpl<>(Collections.singletonList(instanceDTO));

        OrderRequest request = new OrderRequest();
        request.setName("Name");
        request.setDate(LocalDate.now());
        request.setDeliveryMethod("");
        request.setOrderStatus("");
        request.setPaymentMethod("");
        request.setPaymentStatus("");
        request.setPage(0);
        request.setSize(10);
        request.setSortType("ASC");
        request.setSortProperty("name");

        when(service.getAllInstances(any(OrderRequest.class))).thenReturn(page);

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
}

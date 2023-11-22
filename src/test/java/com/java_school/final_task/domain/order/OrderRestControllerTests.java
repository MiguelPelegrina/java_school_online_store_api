package com.java_school.final_task.domain.order;

import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.book.parameter.BookParameterDTO;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatDTO;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodDTO;
import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.impl.OrderRestControllerImpl;
import com.java_school.final_task.domain.order.impl.OrderServiceImpl;
import com.java_school.final_task.domain.order.orderStatus.OrderStatusDTO;
import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodDTO;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusDTO;
import com.java_school.final_task.domain.orderBook.OrderBookJsonDTO;
import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.user.UserDTO;
import com.java_school.final_task.domain.user.userAddress.UserAddressDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.java_school.final_task.domain.userRole.dto.UserRoleJsonDTO;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
        instanceDTO = OrderDTO.builder()
                .id(0)
                .user(createUserDTO())
                .date(LocalDate.now())
                .deliveryMethod(DeliveryMethodDTO.builder().name("DeliveryMethod").isActive(true).build())
                .orderStatus(OrderStatusDTO.builder().name("OrderStatus").isActive(true).build())
                .paymentMethod(PaymentMethodDTO.builder().name("PaymentMethod").isActive(true).build())
                .paymentStatus(PaymentStatusDTO.builder().name("PaymentStatus").isActive(true).build())
                .orderedBooks(List.of(createOrderBookJsonDTO()))
                .build();
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

    private UserDTO createUserDTO() {
        return UserDTO.builder()
                .id(4)
                .isActive(true)
                .email("email@.com")
                .dateOfBirth(LocalDate.now())
                .phone("12345678912")
                .name("Name")
                .surname("Surname")
                .roles(Set.of(UserRoleJsonDTO.builder()
                        .role(RoleDTO.builder()
                                .name("ADMIN")
                                .build())
                        .assignedDate(LocalDate.now())
                        .build()))
                .address(UserAddressDTO.builder()
                        .postalCode(PostalCodeDTO.builder()
                                .isActive(true)
                                .code("Code")
                                .city(CityDTO.builder()
                                        .name("City")
                                        .country(CountryDTO.builder()
                                                .isActive(true)
                                                .name("Country")
                                                .build())
                                        .isActive(true)
                                        .build())
                                .build())
                        .build())
                .build();
    }

    private OrderBookJsonDTO createOrderBookJsonDTO(){
        return OrderBookJsonDTO.builder()
                .id(1)
                .book(BookDTO.builder()
                        .title("Title")
                        .active(true)
                        .genre(new BookGenreDTO("Genre"))
                        .image("Image")
                        .isbn("ISBN")
                        .price(new BigDecimal("1.23"))
                        .stock(9)
                        .parameters(
                                BookParameterDTO.builder()
                                        .author("Author")
                                        .format(new BookParametersFormatDTO("Format"))
                                        .isActive(true)
                                        .build()
                        )
                        .build())
                .amount(1)
                .build();
    }
}

package com.java_school.final_task.domain.user;

import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.user.impl.UserRestControllerImpl;
import com.java_school.final_task.domain.user.impl.UserServiceImpl;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Test class for {@link UserRestControllerImpl}
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl service;

    private UserDTO instanceDTO;

    @BeforeEach
    public void setUp(){
        // Arrange
        instanceDTO = UserDTO.builder()
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

    @Test
    public void UserRestController_GetAllUsersByParams_ReturnUserDTOPage() throws Exception {
        // Arrange
        Page<UserDTO> page = new PageImpl<>(Collections.singletonList(instanceDTO));

        UserRequest request = new UserRequest();
        request.setName("Name");
        request.setActive(Optional.of(true));
        request.setPage(0);
        request.setSize(10);
        request.setSortType("ASC");
        request.setSortProperty("name");

        when(service.getAllInstances(any(UserRequest.class))).thenReturn(page);

        // Act
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/users/search")
                .contentType(MediaType.APPLICATION_JSON)
                .param("active", "true")
                .param("name", "Name")
                .param("page", "0")
                .param("size", "10")
                .param("sortType", "ASC")
                .param("sortProperty", "name"));

        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.totalElements").value(page.getTotalElements()))
                .andExpect(jsonPath("$.content[0].name").value("Name"));
    }
}

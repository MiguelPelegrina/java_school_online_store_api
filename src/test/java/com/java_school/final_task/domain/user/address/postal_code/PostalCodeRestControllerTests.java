package com.java_school.final_task.domain.user.address.postal_code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.city.impl.CityRestControllerImpl;
import com.java_school.final_task.domain.user.user_address.postal_code.impl.PostalCodeServiceImpl;
import mothers.user.address.postal_code.PostalCodeMother;
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
 * Test class for {@link CityRestControllerImpl}
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PostalCodeRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostalCodeServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    private PostalCodeEntity instance;
    private PostalCodeDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = PostalCodeMother.createPostalCode();

        instanceDTO = PostalCodeMother.createPostalCodeDTO();
    }

    @Test
    void PostalCodeController_GetAllPostalCodesByParams_ReturnPostalCodeDTOs() throws Exception {
        // Arrange
        List<PostalCodeDTO> instances = Arrays.asList(instanceDTO);
        when(service.getAllInstances(any(), any())).thenReturn(instances);

        // Act
        ResultActions result = mockMvc.perform(request(HttpMethod.GET, "/postal_codes/search")
                .param("active", String.valueOf(instance.isActive()))
                .param("name", instance.getCode()));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(instances.size())))
                .andExpect(jsonPath("$[0].code", is(instanceDTO.getCode())));
    }
}

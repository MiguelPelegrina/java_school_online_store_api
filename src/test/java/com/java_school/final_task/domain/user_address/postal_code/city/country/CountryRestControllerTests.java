package com.java_school.final_task.domain.user_address.postal_code.city.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.impl.CountryRestControllerImpl;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.impl.CountryServiceImpl;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

/**
 * Test class for {@link CountryRestControllerImpl}. Implements tests for {@link AbstractRestControllerImpl} as well.
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CountryRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    private CountryEntity instance;
    private CountryDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        instance = CountryEntity.builder()
                .isActive(true)
                .name("Spain")
                .build();

        instanceDTO = CountryDTO.builder()
                .isActive(true)
                .name("Spain")
                .build();
    }

    // Tests for the abstract methods
    @Test
    public void CountryController_CreateCountry_ReturnCreated() throws Exception {
        // Arrange
        given(service.saveInstance(any())).willAnswer(invocation -> invocation.getArgument(0));

        // Act
        ResultActions result = mockMvc.perform(post("/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instanceDTO)));

        // Assert
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is(instanceDTO.getName())))
                .andExpect(jsonPath("$.active", CoreMatchers.is(instanceDTO.isActive())));
    }

    // Tests for own methods
    @Test
    public void CountryController_GetAllCountriesByParams_ReturnCountryDTOs() throws Exception {
        // Arrange
        List<CountryDTO> instances = Arrays.asList(instanceDTO);
        when(service.getAllInstances(any(), any())).thenReturn(instances);

        // Act
        ResultActions result = mockMvc.perform(request(HttpMethod.GET,"/countries/search")
                        .param("active", String.valueOf(instance.isActive()))
                        .param("name", instance.getName()));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(instances.size())))
                .andExpect(jsonPath("$[0].name", is(instanceDTO.getName())));
    }
}

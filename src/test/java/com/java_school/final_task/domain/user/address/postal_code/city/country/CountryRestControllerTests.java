package com.java_school.final_task.domain.user.address.postal_code.city.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.impl.CountryRestControllerImpl;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.impl.CountryServiceImpl;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import mothers.user.address.postal_code.city.country.CountryMother;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link CountryRestControllerImpl}. Implements tests for {@link AbstractRestControllerImpl} as well.
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CountryRestControllerTests {
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
        // Arrange
        instance = CountryMother.createCountry();

        instanceDTO = CountryMother.createCountryDTO();
    }

    // Tests for the abstract methods
    @Test
    void CountryController_CreateCountry_ReturnCreated() throws Exception {
        // Arrange
        given(service.saveInstance(instance)).willAnswer(invocation -> invocation.getArgument(0));

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
    void CountryController_GetAllCountriesByParams_ReturnCountryDTOs() throws Exception {
        // Arrange
        List<CountryDTO> instances = Arrays.asList(instanceDTO);
        when(service.getAllInstances(any(), any())).thenReturn(instances);

        // Act
        ResultActions result = mockMvc.perform(request(HttpMethod.GET, "/countries/search")
                .param("active", String.valueOf(instance.isActive()))
                .param("name", instance.getName()));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(instances.size())))
                .andExpect(jsonPath("$[0].name", is(instanceDTO.getName())));
    }

    @Test
    void CountryController_CountryDetail_ReturnCountryDTO() throws Exception {
        // Arrange
        when(service.getInstanceById(instance.getName())).thenReturn(instanceDTO);

        // Act
        ResultActions result = mockMvc.perform(get("/countries/" + instance.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instanceDTO)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(instanceDTO.getName())))
                .andExpect(jsonPath("$.active", CoreMatchers.is(instanceDTO.isActive())));
    }

    @Test
    void CountryController_UpdateCountry_ReturnCountryDTO() throws Exception {
        // Arrange
        when(service.saveInstance(instance)).thenReturn(instanceDTO);

        // Act
        ResultActions result = mockMvc.perform(put("/countries/" + instance.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instanceDTO)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(instanceDTO.getName())))
                .andExpect(jsonPath("$.active", CoreMatchers.is(instanceDTO.isActive())));
    }

    @Test
    void CountryController_DeleteCountry_ReturnStatus() throws Exception {
        // Arrange
        doNothing().when(service).deleteInstance(instance.getName());

        // Act
        ResultActions result = mockMvc.perform(delete("/countries/" + instance.getName())
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk());
    }
}

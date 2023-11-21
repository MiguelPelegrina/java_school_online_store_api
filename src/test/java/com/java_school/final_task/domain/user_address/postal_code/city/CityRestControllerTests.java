package com.java_school.final_task.domain.user_address.postal_code.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.impl.CityRestControllerImpl;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.impl.CityServiceImpl;
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
public class CityRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    private CityEntity instance;
    private CityDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        instance = CityEntity.builder()
                .isActive(true)
                .name("Granada")
                .countryName(CountryEntity.builder()
                        .isActive(true)
                        .name("Spain")
                        .build())
                .build();

        instanceDTO = CityDTO.builder()
                .isActive(true)
                .name("Granada")
                .country(CountryDTO.builder()
                        .isActive(true)
                        .name("Spain")
                        .build())
                .build();
    }

    @Test
    public void CountryController_GetAllCountriesByParams_ReturnCountryDTOs() throws Exception {
        // Arrange
        List<CityDTO> instances = Arrays.asList(instanceDTO);
        when(service.getAllInstances(any(), any())).thenReturn(instances);

        // Act
        mockMvc.perform(request(HttpMethod.GET,"/cities/search")
                        .param("active", String.valueOf(instance.isActive()))
                        .param("name", instance.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(instances.size())))
                .andExpect(jsonPath("$[0].name", is(instanceDTO.getName())));


    }
}

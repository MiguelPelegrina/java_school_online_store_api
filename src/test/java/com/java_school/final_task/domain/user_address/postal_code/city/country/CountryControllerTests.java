package com.java_school.final_task.domain.user_address.postal_code.city.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.impl.CountryServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Test class for the abstract class AbstractService
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CountryControllerTests {
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
                .name("Granada")
                .build();

        instanceDTO = CountryDTO.builder()
                .isActive(true)
                .name("Granada")
                .build();
    }

    @Test
    public void CountryController_CreateCountry_ReturnCreated() throws Exception{
        given(service.saveInstance(any())).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instanceDTO)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(instanceDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active", CoreMatchers.is(instanceDTO.isActive())));
    }

    /*@Test
    public void CountryController_GetAllCountriesByParams_ReturnCountryDTOs() throws Exception {
        // Arrange
        List<CountryDTO> mockCountryDTOList = Arrays.asList(countryDTO);
        when(countryService.getAllInstances(any(), any())).thenReturn(mockCountryDTOList);

        // Act
        mockMvc.perform(request(HttpMethod.GET,"/search")
                        .param("active", String.valueOf(country.isActive()))
                        .param("name", country.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(mockCountryDTOList.size())))
                .andExpect(jsonPath("$[0].propertyName", is(countryDTO)))
                // Add more assertions as needed
                .andReturn();
    }*/
}

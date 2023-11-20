package com.java_school.final_task.service.user.city;

import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityRepository;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.QCityEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.impl.CityServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {
    // Fields
    @Mock
    private CityRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CityServiceImpl service;

    private CityEntity city;

    private CityDTO cityDTO;

    @Before
    public void setUp() {
        city = CityEntity.builder()
                .isActive(true)
                .name("Granada")
                .countryName(CountryEntity.builder()
                        .isActive(true)
                        .name("Spain")
                        .build())
                .build();

        cityDTO = CityDTO.builder()
                .isActive(true)
                .name("Granada")
                .country(CountryDTO.builder()
                        .isActive(true)
                        .name("Spain")
                        .build())
                .build();
    }

    @Test
    public void CityService_GetAllCitiesFiltered_ReturnsCityDTOsPage(){
        // Arrange
        List<CityEntity> cities = new ArrayList<>();
        cities.add(city);

        final QCityEntity qCity = QCityEntity.cityEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qCity.isActive.eq(true));

        when(repository.findAll(queryBuilder)).thenReturn(cities);
        when(modelMapper.map(city, service.getDTOClass())).thenReturn(cityDTO);

        // Act
        List<CityDTO> resultCityDTOs = service.getAllInstances("", Optional.of(true));

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(city, CityDTO.class);
        assertThat(resultCityDTOs).isNotNull();
        assertThat(resultCityDTOs).hasSize(1);
        assertThat(resultCityDTOs.get(0)).isEqualTo(cityDTO);
    }
}

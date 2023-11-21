package com.java_school.final_task.domain.user_address.postal_code.city;

import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityRepository;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.QCityEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.impl.CityServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CityServiceTests {
    // Fields
    @Mock
    private CityRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CityServiceImpl service;

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
    public void CountryService_GetEntityId_ReturnsIdClass(){
        // Arrange
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("TestCity");

        // Act
        String entityId = service.getEntityId(cityEntity);

        // Assert
        assertEquals("TestCity", entityId);
    }

    @Test
    public void CityService_GetAllCitiesFiltered_ReturnsCityDTOsPage(){
        // Arrange
        List<CityEntity> instances = new ArrayList<>();
        instances.add(instance);

        final QCityEntity qInstance = QCityEntity.cityEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qInstance.isActive.eq(true));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<CityDTO> resultDTOs = service.getAllInstances("", Optional.of(true));

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull();
        assertThat(resultDTOs).hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }
}

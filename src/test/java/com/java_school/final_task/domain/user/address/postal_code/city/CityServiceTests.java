package com.java_school.final_task.domain.user.address.postal_code.city;

import com.java_school.final_task.domain.user.user_address.postal_code.city.CityDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.city.CityEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.city.CityRepository;
import com.java_school.final_task.domain.user.user_address.postal_code.city.QCityEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.city.impl.CityServiceImpl;
import com.querydsl.core.BooleanBuilder;
import mothers.user.address.postal_code.city.CityMother;
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
class CityServiceTests {
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
        instance = CityMother.createCity();

        instanceDTO = CityMother.createCityDTO();
    }

    @Test
    void CountryService_GetEntityId_ReturnsIdClass() {
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("City", entityId);
    }

    @Test
    void CityService_GetAllCitiesFiltered_ReturnsCityDTOs() {
        // Arrange
        List<CityEntity> instances = new ArrayList<>();
        instances.add(instance);

        final QCityEntity qInstance = QCityEntity.cityEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qInstance.isActive.eq(true));
        queryBuilder.and(qInstance.countryName.name.containsIgnoreCase("s"));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<CityDTO> resultDTOs = service.getAllInstances("s", Optional.of(true));

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull().hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }

    @Test
    void CityService_GetAllActiveCities_ReturnsCityDTOs() {
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
        assertThat(resultDTOs).isNotNull().hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }
}

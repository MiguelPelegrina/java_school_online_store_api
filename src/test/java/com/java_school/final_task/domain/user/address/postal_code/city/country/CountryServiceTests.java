package com.java_school.final_task.domain.user.address.postal_code.city.country;

import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryRepository;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.QCountryEntity;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.impl.CountryServiceImpl;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import mothers.user.address.postal_code.city.country.CountryMother;
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

/**
 * Test class for {@link CountryServiceImpl}. Implements tests for {@link AbstractServiceImpl} as well.
 */
@ExtendWith(MockitoExtension.class)
class CountryServiceTests {
    // Fields
    @Mock
    private CountryRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CountryServiceImpl service;

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
    void CountryService_CreateCountry_ReturnsSavedCountryDTO() {
        // Arrange
        when(repository.save(any(CountryEntity.class))).thenReturn(instance);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        CountryDTO savedCountry = service.saveInstance(instance);

        // Assert
        assertThat(instanceDTO).isNotNull();
        // Verify might be unnecessary
        verify(repository, times(1)).save(instance);
        verify(modelMapper, times(1)).map(instance, CountryDTO.class);
        assertEquals(instanceDTO, savedCountry);
    }

    @Test
    void CountryService_DeleteCountryById_ReturnsVoid() {
        // Arrange
        lenient().when(repository.findById("Country")).thenReturn(Optional.ofNullable(instance));

        // Act
        service.deleteInstance("Country");

        // Assert
        verify(repository).deleteById("Country");
    }

    @Test
    void CountryService_GetAllCountries_ReturnsCountryDTOs() {
        // Arrange
        CountryEntity instance2 = CountryEntity.builder()
                .isActive(true)
                .name("Germany")
                .build();

        CountryDTO instanceDTO2 = CountryDTO.builder()
                .isActive(true)
                .name("Germany")
                .build();

        List<CountryEntity> countries = new ArrayList<>();
        countries.add(instance);
        countries.add(instance2);

        List<CountryDTO> instanceDTOs = new ArrayList<>();
        instanceDTOs.add(instanceDTO);
        instanceDTOs.add(instanceDTO2);

        when(repository.findAll()).thenReturn(countries);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);
        when(modelMapper.map(instance2, service.getDTOClass())).thenReturn(instanceDTO2);

        // Act
        List<CountryDTO> resultCountryDTOs = service.getAllInstances();

        // Assert
        assertThat(resultCountryDTOs).isNotNull();
        verify(repository, times(1)).findAll();
        verify(modelMapper, times(1)).map(instance, CountryDTO.class);
        verify(modelMapper, times(1)).map(instance2, CountryDTO.class);
        assertEquals(instanceDTOs, resultCountryDTOs);
    }

    @Test
    void CountryService_GetCountryById_ReturnsCountryDTO() {
        // Arrange
        when(repository.findById("Country")).thenReturn(Optional.ofNullable(instance));
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        CountryDTO savedCountry = service.getInstanceById("Country");

        // Assert
        assertThat(savedCountry).isNotNull();
        verify(repository, times(1)).findById("Country");
        verify(modelMapper, times(1)).map(instance, CountryDTO.class);
        assertEquals(instanceDTO, savedCountry);
    }

    // Tests for own methods
    @Test
    void CountryService_GetEntityId_ReturnsIdClass() {
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("Country", entityId);
    }

    @Test
    void CountryService_GetAllCountriesFiltered_ReturnsCountryDTOs() {
        // Arrange
        final QCountryEntity qInstance = QCountryEntity.countryEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        List<CountryEntity> instances = new ArrayList<>();
        instances.add(instance);

        queryBuilder.and(qInstance.isActive.eq(true));
        queryBuilder.and(qInstance.name.containsIgnoreCase("s"));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<CountryDTO> resultDTOs = service.getAllInstances(Optional.of(true), "s");

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull().hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }

    @Test
    void CountryService_GetAllActiveCountries_ReturnsCountryDTOs() {
        // Arrange
        final QCountryEntity qInstance = QCountryEntity.countryEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        List<CountryEntity> instances = new ArrayList<>();
        instances.add(instance);

        queryBuilder.and(qInstance.isActive.eq(true));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<CountryDTO> resultDTOs = service.getAllInstances(Optional.of(true), "");

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull().hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }
}

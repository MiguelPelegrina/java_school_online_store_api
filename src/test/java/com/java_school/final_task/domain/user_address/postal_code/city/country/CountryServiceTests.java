package com.java_school.final_task.domain.user_address.postal_code.city.country;

import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryRepository;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.QCountryEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.impl.CountryServiceImpl;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
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

/**
 * Test class for {@link CountryServiceImpl}. Implements methods to test {@link AbstractServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class CountryServiceTests {
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
        instance = CountryEntity.builder()
                .isActive(true)
                .name("Spain")
                .build();

        instanceDTO = CountryDTO.builder()
                .isActive(true)
                .name("Spain")
                .build();
    }

    @Test
    public void CountryService_GetEntityId_ReturnsIdClass(){
        // Arrange
        CountryEntity instance = new CountryEntity();
        instance.setName("TestCountry");

        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("TestCountry", entityId);
    }

    // Tests for the abstract methods
    @Test
    public void CountryService_CreateCountry_ReturnsSavedCountryDTO(){
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
    public void CountryService_DeleteCountryById(){
        // Arrange
        lenient().when(repository.findById("Spain")).thenReturn(Optional.ofNullable(instance));

        // Act
        service.deleteInstance("Spain");

        // Assert
        verify(repository).deleteById("Spain");
    }

    @Test
    public void CountryService_GetAllCountries_ReturnsCountryDTOs(){
        // Arrange
        CountryEntity instance2 = CountryEntity.builder()
                .isActive(true)
                .name("Málaga")
                .build();

        CountryDTO instanceDTO2 = CountryDTO.builder()
                .isActive(true)
                .name("Málaga")
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
    public void CountryService_GetCountryById_ReturnsCountryDTO(){
        // Arrange
        when(repository.findById("Spain")).thenReturn(Optional.ofNullable(instance));
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        CountryDTO savedCountry = service.getInstanceById("Spain");

        // Assert
        assertThat(savedCountry).isNotNull();
        verify(repository, times(1)).findById("Spain");
        verify(modelMapper, times(1)).map(instance, CountryDTO.class);
        assertEquals(instanceDTO, savedCountry);
    }

    // Tests for own methods
    @Test
    public void CountryService_GetAllCountriesFiltered_ReturnsCountryDTOs(){
        // Arrange
        final QCountryEntity qInstance = QCountryEntity.countryEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        List<CountryEntity> instances = new ArrayList<>();
        instances.add(instance);

        queryBuilder.and(qInstance.isActive.eq(true));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<CountryDTO> resultDTOs = service.getAllInstances(Optional.of(true),"");

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull();
        assertThat(resultDTOs).hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }
}

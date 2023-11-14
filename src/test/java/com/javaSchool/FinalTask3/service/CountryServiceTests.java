package com.javaSchool.FinalTask3.service;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryRepository;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.QCountryEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.impl.CountryServiceImpl;
import com.querydsl.core.BooleanBuilder;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Class just for practice purposes
 */
@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTests {
    @Mock
    private CountryRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CountryServiceImpl service;

    @Test
    public void CountryService_CreateCountry_ReturnsSavedCountryDTO(){
        // Arrange
        CountryEntity country = CountryEntity.builder()
                .isActive(true)
                .name("Granada")
                .build();

        CountryDTO countryDTO = CountryDTO.builder()
                .isActive(true)
                .name("Granada")
                .build();

        when(repository.save(any(CountryEntity.class))).thenReturn(country);
        when(modelMapper.map(country, service.getDTOClass())).thenReturn(countryDTO);

        // Act
        CountryDTO savedCountry = service.saveInstance(country);

        // Assert
        // TODO Not sure if too much, just enough, or something missing
        assertThat(countryDTO).isNotNull();
        // Verify might be unnecessary
        verify(repository, times(1)).save(country);
        verify(modelMapper, times(1)).map(country, CountryDTO.class);
        assertEquals(countryDTO, savedCountry);
    }

    // TODO Not sure if right
    @Test
    public void CountryService_DeleteCountryById(){
        // Arrange
        CountryEntity country = CountryEntity.builder()
                .isActive(true)
                .name("Granada")
                .build();

        // TODO Following stubbings are unnecessary (click to navigate to relevant line of code)
        when(repository.findById("Granada")).thenReturn(Optional.ofNullable(country));

        // Act
        service.deleteInstance("Granada");

        // Assert
        verify(repository).deleteById("Granada");
    }

    @Test
    public void CountryService_GetAllCountries_ReturnsCountryDTOs(){
        // Arrange
        CountryEntity country = CountryEntity.builder()
                .isActive(true)
                .name("Granada")
                .build();

        CountryDTO countryDTO = CountryDTO.builder()
                .isActive(true)
                .name("Granada")
                .build();

        CountryEntity country2 = CountryEntity.builder()
                .isActive(true)
                .name("Málaga")
                .build();

        CountryDTO countryDTO2 = CountryDTO.builder()
                .isActive(true)
                .name("Málaga")
                .build();

        List<CountryEntity> countries = new ArrayList<>();
        countries.add(country);
        countries.add(country2);

        List<CountryDTO> countryDTOs = new ArrayList<>();
        countryDTOs.add(countryDTO);
        countryDTOs.add(countryDTO2);

        when(repository.findAll()).thenReturn(countries);
        when(modelMapper.map(country, service.getDTOClass())).thenReturn(countryDTO);
        when(modelMapper.map(country2, service.getDTOClass())).thenReturn(countryDTO2);

        // Act
        List<CountryDTO> resultCountryDTOs = service.getAllInstances();

        // Assert
        assertThat(resultCountryDTOs).isNotNull();
        verify(repository, times(1)).findAll();
        verify(modelMapper, times(1)).map(country, CountryDTO.class);
        verify(modelMapper, times(1)).map(country2, CountryDTO.class);
        assertEquals(countryDTOs, resultCountryDTOs);
    }

    @Test
    public void CountryService_GetAllCountriesFiltered_ReturnsCountryDTOsPage(){
        // Arrange
        CountryEntity country = CountryEntity.builder()
                .isActive(true)
                .name("Granada")
                .build();

        CountryDTO countryDTO = CountryDTO.builder()
                .isActive(true)
                .name("Granada")
                .build();

        CountryEntity country2 = CountryEntity.builder()
                .isActive(false)
                .name("Málaga")
                .build();

        CountryDTO countryDTO2 = CountryDTO.builder()
                .isActive(false)
                .name("Málaga")
                .build();

        List<CountryEntity> countries = new ArrayList<>();
        countries.add(country);

        final QCountryEntity qCountry = QCountryEntity.countryEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qCountry.isActive.eq(true));

        when(repository.findAll(queryBuilder)).thenReturn(countries);
        when(modelMapper.map(country, service.getDTOClass())).thenReturn(countryDTO);

        // Act
        List<CountryDTO> resultCountryDTOs = service.getAllInstances(Optional.of(true),"");

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(country, CountryDTO.class);
        assertThat(resultCountryDTOs).isNotNull();
        assertThat(resultCountryDTOs).hasSize(1);
        assertThat(resultCountryDTOs.get(0)).isEqualTo(countryDTO);
    }

    @Test
    public void CountryService_GetCountryById_ReturnsCountryDTO(){
        // Arrange
        CountryEntity country = CountryEntity.builder()
                .isActive(true)
                .name("Granada")
                .build();

        CountryDTO countryDTO = CountryDTO.builder()
                .isActive(true)
                .name("Granada")
                .build();

        when(repository.findById("Granada")).thenReturn(Optional.ofNullable(country));
        when(modelMapper.map(country, service.getDTOClass())).thenReturn(countryDTO);

        // Act
        CountryDTO savedCountry = service.getInstanceById("Granada");

        // Assert
        assertThat(savedCountry).isNotNull();
        verify(repository, times(1)).findById("Granada");
        verify(modelMapper, times(1)).map(country, CountryDTO.class);
        assertEquals(countryDTO, savedCountry);
    }

    // TODO Most likely redundant?
    @Test
    public void CountryService_UpdateCountry_ReturnsSavedCountryDTOs(){
        CountryEntity country = CountryEntity.builder()
                .isActive(true)
                .name("Granada")
                .build();

        service.saveInstance(country);

        CountryDTO countryDTO = CountryDTO.builder()
                .isActive(false)
                .name("Granada")
                .build();

        country.setActive(false);

        when(repository.save(any(CountryEntity.class))).thenReturn(country);
        when(modelMapper.map(country, service.getDTOClass())).thenReturn(countryDTO);

        // Act
        CountryDTO resultCountryDTO = service.saveInstance(country);

        // Assert
        assertThat(resultCountryDTO).isNotNull();
        verify(repository, times(2)).save(country);
        verify(modelMapper, times(1)).map(country, CountryDTO.class);
        assertEquals(countryDTO, resultCountryDTO);
    }
}

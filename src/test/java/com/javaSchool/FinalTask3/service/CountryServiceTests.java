package com.javaSchool.FinalTask3.service;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryRepository;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.impl.CountryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

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
    public void CountryService_CreateCountry_ReturnsSavedCountry(){
        // Arrange
        CountryEntity country = CountryEntity.builder()
                .isActive(true)
                .name("Granada")
                .build();

        CountryDTO countryDTO = CountryDTO.builder()
                .isActive(true)
                .name("Granada")
                .build();

        // Act
        when(repository.save(Mockito.any(CountryEntity.class))).thenReturn(country);
        when(modelMapper.map(country, CountryDTO.class)).thenReturn(countryDTO);

        CountryDTO resultDTO = service.saveInstance(country);

        // Assert
        assertThat(countryDTO).isNotNull();
        verify(repository, times(1)).save(country);
        verify(modelMapper, times(1)).map(country, CountryDTO.class);
        assertEquals(countryDTO, resultDTO);
    }
}

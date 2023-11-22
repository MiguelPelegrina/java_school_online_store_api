package com.java_school.final_task.domain.user.address.postal_code;

import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeRepository;
import com.java_school.final_task.domain.user.userAddress.postalCode.QPostalCodeEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.impl.PostalCodeServiceImpl;
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
 * Test class for {@link PostalCodeServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class PostalCodeServiceTests {
    // Fields
    @Mock
    private PostalCodeRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostalCodeServiceImpl service;

    private PostalCodeEntity instance;
    private PostalCodeDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = PostalCodeEntity.builder()
                .isActive(true)
                .code("Code")
                .city(CityEntity.builder()
                        .name("City")
                        .countryName(CountryEntity.builder()
                                .isActive(true)
                                .name("Spain")
                                .build())
                        .isActive(true)
                        .build())
                .build();

        instanceDTO = PostalCodeDTO.builder()
                .isActive(true)
                .code("Code")
                .city(CityDTO.builder()
                        .name("City")
                        .country(CountryDTO.builder()
                                .isActive(true)
                                .name("Country")
                                .build())
                        .isActive(true)
                        .build())
                .build();
    }

    @Test
    public void PostalCodeService_GetEntityId_ReturnsIdClass(){
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("Code", entityId);
    }

    @Test
    public void PostalCodeService_GetAllPostalCodesFiltered_ReturnsPostalCodeDTOs(){
        // Arrange
        List<PostalCodeEntity> instances = new ArrayList<>();
        instances.add(instance);

        final QPostalCodeEntity qInstance  = QPostalCodeEntity.postalCodeEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qInstance.isActive.eq(true));
        queryBuilder.and(qInstance.city.name.containsIgnoreCase("g"));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<PostalCodeDTO> resultDTOs = service.getAllInstances("g", Optional.of(true));

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull();
        assertThat(resultDTOs).hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }
}

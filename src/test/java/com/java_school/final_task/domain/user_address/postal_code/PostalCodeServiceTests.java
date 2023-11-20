package com.java_school.final_task.domain.user_address.postal_code;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link PostalCodeServiceImpl}
 */
@RunWith(MockitoJUnitRunner.class)
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

    @Before
    public void setUp() {
        instance = PostalCodeEntity.builder()
                .isActive(true)
                .code("18014")
                .city(CityEntity.builder()
                        .name("Granada")
                        .countryName(CountryEntity.builder()
                                .isActive(true)
                                .name("Spain")
                                .build())
                        .isActive(true)
                        .build())
                .build();

        instanceDTO = PostalCodeDTO.builder()
                .isActive(true)
                .code("18014")
                .city(CityDTO.builder()
                        .name("Granada")
                        .country(CountryDTO.builder()
                                .isActive(true)
                                .name("Spain")
                                .build())
                        .isActive(true)
                        .build())
                .build();
    }

    @Test
    public void PostalCodeService_GetEntityId_ReturnsIdClass(){
        // Arrange
        PostalCodeEntity instance = new PostalCodeEntity();
        instance.setCode("TestPostalCode");

        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("TestPostalCode", entityId);
    }

    @Test
    public void PostalCodeService_GetAllPostalCodesFiltered_ReturnsPostalCodeDTOsPage(){
        // Arrange
        List<PostalCodeEntity> instances = new ArrayList<>();
        instances.add(instance);

        final QPostalCodeEntity qInstance  = QPostalCodeEntity.postalCodeEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qInstance.isActive.eq(true));

        when(repository.findAll(queryBuilder)).thenReturn(instances);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        List<PostalCodeDTO> resultDTOs = service.getAllInstances("", Optional.of(true));

        // Assert
        verify(repository, times(1)).findAll(queryBuilder);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull();
        assertThat(resultDTOs).hasSize(1);
        assertThat(resultDTOs.get(0)).isEqualTo(instanceDTO);
    }
}

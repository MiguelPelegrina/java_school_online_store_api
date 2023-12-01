package com.java_school.final_task.domain.user.user_address.postal_code.city.impl;

import com.java_school.final_task.domain.user.user_address.postal_code.city.*;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryRepository;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryEntity;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link CityRepository} and the
 * {@link CityRestControllerImpl}. Obtains data from the
 * {@link CityRepository} and returns the object(s) of the entity {@link CityEntity} as
 * {@link CityDTO} to the {@link CityRestControllerImpl}.
 */
@Service
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
public class CityServiceImpl
        extends AbstractServiceImpl<CityRepository, CityEntity, CityDTO, String>
        implements CityService {
    /**
     * All arguments constructor.
     *
     * @param repository        {@link CityRepository} of the {@link CityEntity} instance.
     * @param modelMapper       ModelMapper that converts the {@link CityRepository} to {@link CityDTO}
     * @param countryRepository {@link CountryRepository} to access the instance of the
     *                          {@link CountryEntity} of this {@link CityEntity} instance.
     */
    public CityServiceImpl(CityRepository repository, ModelMapper modelMapper, CountryRepository countryRepository) {
        super(repository, modelMapper);
    }

    @Override
    public Class<CityDTO> getDTOClass() {
        return CityDTO.class;
    }

    @Override
    public String getEntityId(CityEntity instance) {
        return instance.getName();
    }

    @Override
    public List<CityDTO> getAllInstances(String countryName, Optional<Boolean> active) {
        // Variables
        final QCityEntity qCity = QCityEntity.cityEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        active.ifPresent(aBoolean -> queryBuilder.and(qCity.isActive.eq(aBoolean)));
        if (!countryName.isEmpty()) {
            queryBuilder.and(qCity.countryName.name.containsIgnoreCase(countryName));
        }

        // Retrieve a list of city instances from the repository, map them to DTOs, and collect them into a List.
        return this.repository.findAll(queryBuilder).stream().map(city -> modelMapper.map(city, getDTOClass())).collect(Collectors.toList());
    }
}

package com.java_school.final_task.domain.user.user_address.postal_code.city.country.impl;

import com.java_school.final_task.domain.user.user_address.postal_code.city.country.*;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link CountryRepository} and the
 * {@link CountryRestControllerImpl}. Obtains data from the
 * {@link CountryRepository} and returns the object(s) of the entity {@link CountryEntity} as
 * {@link CountryDTO} to the {@link CountryRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
@Service
public class CountryServiceImpl
        extends AbstractServiceImpl<CountryRepository, CountryEntity, CountryDTO, String>
        implements CountryService {
    /**
     * All arguments constructor.
     *
     * @param repository  {@link CountryRepository} of the {@link CountryEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link CountryEntity} to {@link CountryDTO}
     */
    public CountryServiceImpl(CountryRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<CountryDTO> getDTOClass() {
        return CountryDTO.class;
    }

    @Override
    public String getEntityId(CountryEntity instance) {
        return instance.getName();
    }

    @Override
    public List<CountryDTO> getAllInstances(Optional<Boolean> active, String countryName) {
        // Variables
        final QCountryEntity qCountry = QCountryEntity.countryEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        active.ifPresent(aBoolean -> queryBuilder.and(qCountry.isActive.eq(aBoolean)));
        if (!countryName.isEmpty()) {
            queryBuilder.and(qCountry.name.containsIgnoreCase(countryName));
        }

        // Retrieve a list of countries from the repository, map them to DTOs, and collect them into a List.
        return this.repository.findAll(queryBuilder).stream().map(
                        country -> modelMapper.map(country, getDTOClass()))
                .collect(Collectors.toList());
    }
}

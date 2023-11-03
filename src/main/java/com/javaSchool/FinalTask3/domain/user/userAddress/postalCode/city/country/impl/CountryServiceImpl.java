package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.*;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO Not sure if I should try to implement the Spring transaction routing mentioned in
//  https://vladmihalcea.com/read-write-read-only-transaction-routing-spring/
/**
 * Service class responsible for the interaction between the {@link CountryRepository} and the
 * {@link CountryRestControllerImpl}. Obtains data from the
 * {@link CountryRepository} and returns the object(s) of the entity {@link CountryEntity} as
 * {@link CountryDTO} to the {@link CountryRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class CountryServiceImpl
        extends AbstractServiceImpl<CountryRepository, CountryEntity, CountryDTO, String>
        implements CountryService {
    /**
     * All arguments constructor.
     * @param repository {@link CountryRepository} of the {@link CountryEntity} entity.
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
    public List<CountryDTO> getAllInstances(Optional<Boolean> active, String countryName){
        // Variables
        final QCountryEntity qCountry = QCountryEntity.countryEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        active.ifPresent(aBoolean -> queryBuilder.and(qCountry.isActive.eq(aBoolean)));
        if(!countryName.isEmpty()){
            queryBuilder.and(qCountry.name.containsIgnoreCase(countryName));
        }

        // Retrieve a list of countries from the repository, map them to DTOs, and collect them into a List.
        return this.repository.findAll(queryBuilder).stream().map(
                country -> modelMapper.map(country, getDTOClass()))
                .collect(Collectors.toList());
    }
}

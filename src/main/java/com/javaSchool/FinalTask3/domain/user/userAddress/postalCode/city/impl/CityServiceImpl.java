package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.*;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryRepository;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly = true)
@Secured("ROLE_ADMIN")
public class CityServiceImpl
        extends AbstractServiceImpl<CityRepository, CityEntity, CityDTO, String>
        implements CityService {
    // TODO Not sure if necessary
    private final CountryRepository countryRepository;

    /**
     * All arguments constructor.
     *
     * @param repository        {@link CityRepository} of the {@link CityEntity} instance.
     * @param modelMapper       ModelMapper that converts the {@link CityRepository} to {@link CityDTO}
     * @param countryRepository {@link CountryRepository} to access the instance of the
     * {@link CountryEntity} of this {@link CityEntity} instance.
     */
    public CityServiceImpl(CityRepository repository, ModelMapper modelMapper, CountryRepository countryRepository) {
        super(repository, modelMapper);
        // TODO Not sure if necessary
        this.countryRepository = countryRepository;
    }

    // TODO Not sure if necessary
    @Override
    @Transactional
    public CityDTO saveInstance(CityEntity instance){
        instance.setCountryName(countryRepository.getReferenceById(instance.getCountryName().getName()));
        return modelMapper.map(repository.save(instance), getDTOClass());
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
        if(!countryName.isEmpty()){
            queryBuilder.and(qCity.countryName.name.containsIgnoreCase(countryName));
        }

        // Retrieve a list of city instances from the repository, map them to DTOs, and collect them into a List.
        return this.repository.findAll(queryBuilder).stream().map(city -> modelMapper.map(city, getDTOClass())).collect(Collectors.toList());
    }
}

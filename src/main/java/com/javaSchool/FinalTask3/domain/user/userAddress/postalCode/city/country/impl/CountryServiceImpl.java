package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryRepository;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.QCountryEntity;
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
public class CountryServiceImpl extends AbstractServiceImpl<CountryEntity, CountryDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link CountryRepository} of the {@link CountryEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link CountryEntity} to {@link CountryDTO}
     */
    public CountryServiceImpl(CountryRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link CountryEntity} entity.
     * @return Returns the {@link CountryDTO} class.
     */
    @Override
    protected Class<CountryDTO> getDTOClass() {
        return CountryDTO.class;
    }

    @Override
    protected String getEntityId(CountryEntity instance) {
        return instance.getName();
    }

    public List<CountryDTO> getAllInstances(Optional<Boolean> active){
       final CountryRepository countryRepository = (CountryRepository) this.repository;
       final QCountryEntity qCountry = QCountryEntity.countryEntity;
       final BooleanBuilder queryBuilder = new BooleanBuilder();

       active.ifPresent(aBoolean -> queryBuilder.and(qCountry.isActive.eq(aBoolean)));

       return countryRepository.findAll(queryBuilder).stream().map(country -> modelMapper.map(country, getDTOClass())).collect(Collectors.toList());
    }
}

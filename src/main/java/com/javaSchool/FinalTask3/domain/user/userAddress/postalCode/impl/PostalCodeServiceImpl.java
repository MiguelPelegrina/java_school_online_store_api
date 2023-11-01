package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeRepository;
import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.QPostalCodeEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link PostalCodeRepository} and the {@link PostalCodeRestControllerImpl}.
 * Obtains data from the  {@link PostalCodeRepository} and returns the object(s) of the entity {@link PostalCodeEntity} as
 * {@link PostalCodeDTO} to the {@link PostalCodeRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class PostalCodeServiceImpl extends AbstractServiceImpl<PostalCodeEntity, PostalCodeDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link PostalCodeRepository} of the {@link PostalCodeEntity}.
     * @param modelMapper ModelMapper that converts the {@link PostalCodeEntity} to {@link PostalCodeDTO}
     */
    public PostalCodeServiceImpl(PostalCodeRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link PostalCodeEntity}.
     * @return Returns the {@link PostalCodeDTO} class.
     */
    @Override
    protected Class<PostalCodeDTO> getDTOClass() {
        return PostalCodeDTO.class;
    }

    @Override
    protected String getEntityId(PostalCodeEntity instance) {
        return instance.getCode();
    }

    public List<PostalCodeDTO> getAllInstances(String cityName, Optional<Boolean> active){
        // Variables
        final PostalCodeRepository postalCodeRepository = (PostalCodeRepository) this.repository;
        final QPostalCodeEntity qPostalCode = QPostalCodeEntity.postalCodeEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        active.ifPresent(aBoolean -> queryBuilder.and(qPostalCode.isActive.eq(aBoolean)));
        if(!cityName.isEmpty()){
            queryBuilder.and(qPostalCode.city.name.containsIgnoreCase(cityName));
        }

        return postalCodeRepository.findAll(queryBuilder).stream().map(postalCode -> modelMapper.map(postalCode, getDTOClass())).collect(Collectors.toList());
    }
}

package com.java_school.final_task.domain.user.user_address.postal_code.impl;

import com.java_school.final_task.domain.user.user_address.postal_code.*;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for the interaction between the {@link PostalCodeRepository} and the {@link PostalCodeRestControllerImpl}.
 * Obtains data from the  {@link PostalCodeRepository} and returns the object(s) of the entity {@link PostalCodeEntity} as
 * {@link PostalCodeDTO} to the {@link PostalCodeRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
@Service
public class PostalCodeServiceImpl
        extends AbstractServiceImpl<PostalCodeRepository, PostalCodeEntity, PostalCodeDTO, String>
        implements PostalCodeService {
    /**
     * All arguments constructor.
     *
     * @param repository  {@link PostalCodeRepository} of the {@link PostalCodeEntity}.
     * @param modelMapper ModelMapper that converts the {@link PostalCodeEntity} to {@link PostalCodeDTO}
     */
    public PostalCodeServiceImpl(PostalCodeRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<PostalCodeDTO> getDTOClass() {
        return PostalCodeDTO.class;
    }

    @Override
    public String getEntityId(PostalCodeEntity instance) {
        return instance.getCode();
    }

    @Override
    public List<PostalCodeDTO> getAllInstances(String cityName, Optional<Boolean> active) {
        // Variables
        final QPostalCodeEntity qPostalCode = QPostalCodeEntity.postalCodeEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        active.ifPresent(aBoolean -> queryBuilder.and(qPostalCode.isActive.eq(aBoolean)));
        if (!cityName.isEmpty()) {
            queryBuilder.and(qPostalCode.city.name.containsIgnoreCase(cityName));
        }

        return this.repository.findAll(queryBuilder).stream().map(
                        postalCode -> modelMapper.map(postalCode, getDTOClass()))
                .collect(Collectors.toList());
    }
}

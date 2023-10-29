package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode;

import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

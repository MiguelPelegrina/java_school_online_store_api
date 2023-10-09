package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.PostalCodeDTO;
import com.javaSchool.FinalTask3.entities.PostalCode;
import com.javaSchool.FinalTask3.repositories.PostalCodeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link PostalCodeRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.PostalCodeController}. Obtains data from the
 * {@link PostalCodeRepository} and returns the object(s) of the entity {@link PostalCode} as
 * {@link PostalCodeDTO} to the {@link com.javaSchool.FinalTask3.controller.PostalCodeController}.
 */
@Service
@Transactional(readOnly = true)
public class PostalCodeService extends BaseServiceWithUpdate<PostalCode, PostalCodeDTO, String>{
    /**
     * All arguments constructor.
     * @param repository {@link PostalCodeRepository} of the {@link PostalCode} entity.
     * @param modelMapper ModelMapper that converts the {@link PostalCode} to {@link PostalCodeDTO}
     */
    public PostalCodeService(PostalCodeRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link PostalCode} entity.
     * @return Returns the {@link PostalCodeDTO} class.
     */
    @Override
    protected Class<PostalCodeDTO> getDTOClass() {
        return PostalCodeDTO.class;
    }

    /**
     * Updates the values of an existing {@link PostalCode} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(PostalCode existingInstance, PostalCode newInstance) {
        existingInstance.setActive(newInstance.isActive());
        existingInstance.setCity(newInstance.getCity());
    }
}

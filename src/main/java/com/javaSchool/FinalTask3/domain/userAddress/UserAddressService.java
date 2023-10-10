package com.javaSchool.FinalTask3.domain.userAddress;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link UserAddressRepository} and the
 * {@link UserAddressController}. Obtains data from the
 * {@link UserAddressRepository} and returns the object(s) of the entity {@link UserAddressEntity} as
 * {@link UserAddressDTO} to the {@link UserAddressController}.
 */
@Service
@Transactional(readOnly = true)
public class UserAddressService extends AbstractServiceWithUpdate<UserAddressEntity, UserAddressDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link UserAddressRepository} of the {@link UserAddressEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link UserAddressEntity} to {@link UserAddressDTO}
     */
    public UserAddressService(UserAddressRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link UserAddressEntity} entity.
     * @return Returns the {@link UserAddressDTO} class.
     */
    @Override
    protected Class<UserAddressDTO> getDTOClass() {
        return UserAddressDTO.class;
    }

    /**
     * Updates the values of an existing {@link UserAddressEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(UserAddressEntity existingInstance, UserAddressEntity newInstance) {
        existingInstance.setPostalCode(newInstance.getPostalCode());
        existingInstance.setUserId(newInstance.getUserId());
        existingInstance.setStreet(newInstance.getStreet());
        existingInstance.setNumber(newInstance.getNumber());
        existingInstance.setActive(newInstance.isActive());
    }
}

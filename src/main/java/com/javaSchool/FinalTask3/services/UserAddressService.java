package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.UserAddressDTO;
import com.javaSchool.FinalTask3.entities.UserAddress;
import com.javaSchool.FinalTask3.repositories.UserAddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link UserAddressRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.UserAddressController}. Obtains data from the
 * {@link UserAddressRepository} and returns the object(s) of the entity {@link UserAddress} as
 * {@link UserAddressDTO} to the {@link com.javaSchool.FinalTask3.controller.UserAddressController}.
 */
@Service
@Transactional(readOnly = true)
public class UserAddressService extends BaseServiceWithUpdate<UserAddress, UserAddressDTO, Integer>{
    /**
     * All arguments constructor.
     * @param repository {@link UserAddressRepository} of the {@link UserAddress} entity.
     * @param modelMapper ModelMapper that converts the {@link UserAddress} to {@link UserAddressDTO}
     */
    public UserAddressService(UserAddressRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link UserAddress} entity.
     * @return Returns the {@link UserAddressDTO} class.
     */
    @Override
    protected Class<UserAddressDTO> getDTOClass() {
        return UserAddressDTO.class;
    }

    /**
     * Updates the values of an existing {@link UserAddress} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(UserAddress existingInstance, UserAddress newInstance) {
        existingInstance.setPostalCode(newInstance.getPostalCode());
        existingInstance.setUserId(newInstance.getUserId());
        existingInstance.setStreet(newInstance.getStreet());
        existingInstance.setNumber(newInstance.getNumber());
        existingInstance.setActive(newInstance.isActive());
    }
}

package com.javaSchool.FinalTask3.domain.user.userAddress.impl;

import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressDTO;
import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressEntity;
import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link UserAddressRepository} and the
 * {@link UserAddressRestControllerImpl}. Obtains data from the
 * {@link UserAddressRepository} and returns the object(s) of the entity {@link UserAddressEntity} as
 * {@link UserAddressDTO} to the {@link UserAddressRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class UserAddressServiceImpl extends AbstractServiceImpl<UserAddressEntity, UserAddressDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link UserAddressRepository} of the {@link UserAddressEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link UserAddressEntity} to {@link UserAddressDTO}
     */
    public UserAddressServiceImpl(UserAddressRepository repository, ModelMapper modelMapper) {
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

    @Override
    protected Integer getEntityId(UserAddressEntity instance) {
        return instance.getId();
    }
}

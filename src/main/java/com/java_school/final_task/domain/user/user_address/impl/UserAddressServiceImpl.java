package com.java_school.final_task.domain.user.user_address.impl;

import com.java_school.final_task.domain.user.user_address.UserAddressDTO;
import com.java_school.final_task.domain.user.user_address.UserAddressEntity;
import com.java_school.final_task.domain.user.user_address.UserAddressRepository;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for the interaction between the {@link UserAddressRepository} and the
 * {@link UserAddressRestControllerImpl}. Obtains data from the
 * {@link UserAddressRepository} and returns the object(s) of the entity {@link UserAddressEntity} as
 * {@link UserAddressDTO} to the {@link UserAddressRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_CLIENT"})
@Service
public class UserAddressServiceImpl
        extends AbstractServiceImpl<UserAddressRepository, UserAddressEntity, UserAddressDTO, Integer> {
    /**
     * All arguments constructor.
     *
     * @param repository  {@link UserAddressRepository} of the {@link UserAddressEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link UserAddressEntity} to {@link UserAddressDTO}
     */
    public UserAddressServiceImpl(UserAddressRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<UserAddressDTO> getDTOClass() {
        return UserAddressDTO.class;
    }

    @Override
    public Integer getEntityId(UserAddressEntity instance) {
        return instance.getId();
    }
}

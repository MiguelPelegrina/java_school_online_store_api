package com.javaSchool.FinalTask3.domain.userRole.impl;

import com.javaSchool.FinalTask3.domain.userRole.dto.UserRoleDTO;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleEntity;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for the interaction between the {@link UserRoleRepository} and the
 * {@link UserRoleRestControllerImpl}. Obtains data from the
 * {@link UserRoleRepository} and returns the object(s) of the entity {@link UserRoleEntity} as
 * {@link UserRoleDTO} to the {@link UserRoleRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
@Service
public class UserRoleServiceImpl
        extends AbstractServiceImpl<UserRoleRepository, UserRoleEntity, UserRoleDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link UserRoleRepository} of the {@link UserRoleEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link UserRoleEntity} to {@link UserRoleDTO}
     */
    public UserRoleServiceImpl(UserRoleRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<UserRoleDTO> getDTOClass() {
        return UserRoleDTO.class;
    }

    @Override
    public Integer getEntityId(UserRoleEntity instance) {
        return instance.getId();
    }
}

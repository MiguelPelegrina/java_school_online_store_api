package com.java_school.final_task.domain.role.impl;

import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.role.RoleEntity;
import com.java_school.final_task.domain.role.RoleRepository;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for the interaction between the {@link RoleRepository} and the {@link RoleRestControllerImpl}.
 * Obtains data from the {@link RoleRepository} and returns the object(s) of the {@link RoleEntity} as
 * {@link RoleDTO} to the {@link RoleRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN"})
@Service
public class RoleServiceImpl
        extends AbstractServiceImpl<RoleRepository, RoleEntity, RoleDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link RoleRepository} of the {@link RoleEntity} instance.
     * @param modelMapper ModelMapper that converts the {@link RoleEntity} instance to {@link RoleDTO}
     */
    public RoleServiceImpl(RoleRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public Class<RoleDTO> getDTOClass() {
        return RoleDTO.class;
    }

    @Override
    public String getEntityId(RoleEntity instance) {
        return instance.getName();
    }
}

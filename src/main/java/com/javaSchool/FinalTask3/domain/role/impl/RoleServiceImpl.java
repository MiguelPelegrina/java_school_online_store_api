package com.javaSchool.FinalTask3.domain.role.impl;

import com.javaSchool.FinalTask3.domain.role.RoleDTO;
import com.javaSchool.FinalTask3.domain.role.RoleEntity;
import com.javaSchool.FinalTask3.domain.role.RoleRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link RoleRepository} and the {@link RoleRestControllerImpl}.
 * Obtains data from the {@link RoleRepository} and returns the object(s) of the {@link RoleEntity} as
 * {@link RoleDTO} to the {@link RoleRestControllerImpl}.
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends AbstractServiceImpl<RoleEntity, RoleDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link RoleRepository} of the {@link RoleEntity} instance.
     * @param modelMapper ModelMapper that converts the {@link RoleEntity} instance to {@link RoleDTO}
     */
    public RoleServiceImpl(RoleRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link RoleEntity}.
     * @return Returns the {@link RoleDTO} class.
     */
    @Override
    protected Class<RoleDTO> getDTOClass() {
        return RoleDTO.class;
    }

    @Override
    protected String getEntityId(RoleEntity instance) {
        return instance.getName();
    }
}

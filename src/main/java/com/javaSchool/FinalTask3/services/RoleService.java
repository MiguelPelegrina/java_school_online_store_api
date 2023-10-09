package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.RoleDTO;
import com.javaSchool.FinalTask3.entities.Role;
import com.javaSchool.FinalTask3.repositories.RoleRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link RoleRepository} and the
 * {@link com.javaSchool.FinalTask3.controller.RoleController}. Obtains data from the
 * {@link RoleRepository} and returns the object(s) of the entity {@link Role} as
 * {@link RoleDTO} to the {@link com.javaSchool.FinalTask3.controller.RoleController}.
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends BaseServiceWithUpdate<Role, RoleDTO, String>{
    /**
     * All arguments constructor.
     * @param repository {@link RoleRepository} of the {@link Role} entity.
     * @param modelMapper ModelMapper that converts the {@link Role} to {@link RoleDTO}
     */
    public RoleService(RoleRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link Role} entity.
     * @return Returns the {@link RoleDTO} class.
     */
    @Override
    protected Class<RoleDTO> getDTOClass() {
        return RoleDTO.class;
    }

    /**
     * Updates the values of an existing {@link Role} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(Role existingInstance, Role newInstance) {
        existingInstance.setUserRoles(newInstance.getUserRoles());
    }
}

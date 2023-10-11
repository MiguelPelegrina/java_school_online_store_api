package com.javaSchool.FinalTask3.domain.role;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link RoleRepository} and the {@link RoleRestController}.
 * Obtains data from the {@link RoleRepository} and returns the object(s) of the {@link RoleEntity} as
 * {@link RoleDTO} to the {@link RoleRestController}.
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends AbstractServiceWithUpdate<RoleEntity, RoleDTO, String> {
    /**
     * All arguments constructor.
     * @param repository {@link RoleRepository} of the {@link RoleEntity} instance.
     * @param modelMapper ModelMapper that converts the {@link RoleEntity} instance to {@link RoleDTO}
     */
    public RoleService(RoleRepository repository, ModelMapper modelMapper) {
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

    /**
     * Updates the values of an existing {@link RoleEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(RoleEntity existingInstance, RoleEntity newInstance) {
        existingInstance.setUserRoles(newInstance.getUserRoles());
    }
}

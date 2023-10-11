package com.javaSchool.FinalTask3.domain.userRole;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link UserRoleRepository} and the
 * {@link UserRoleRestController}. Obtains data from the
 * {@link UserRoleRepository} and returns the object(s) of the entity {@link UserRoleEntity} as
 * {@link UserRoleDTO} to the {@link UserRoleRestController}.
 */
@Service
@Transactional(readOnly = true)
public class UserRoleService extends AbstractServiceWithUpdate<UserRoleEntity, UserRoleDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link UserRoleRepository} of the {@link UserRoleEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link UserRoleEntity} to {@link UserRoleDTO}
     */
    public UserRoleService(UserRoleRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link UserRoleEntity} entity.
     * @return Returns the {@link UserRoleDTO} class.
     */
    @Override
    protected Class<UserRoleDTO> getDTOClass() {
        return UserRoleDTO.class;
    }

    @Override
    protected Integer getEntityId(UserRoleEntity instance) {
        return instance.getId();
    }

    /**
     * Updates the values of an existing {@link UserRoleEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(UserRoleEntity existingInstance, UserRoleEntity newInstance) {
        existingInstance.setUser(newInstance.getUser());
        existingInstance.setRole(newInstance.getRole());
        existingInstance.setAssignedDate(newInstance.getAssignedDate());
    }
}

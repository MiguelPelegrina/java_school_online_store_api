package com.javaSchool.FinalTask3.domain.role;

import com.javaSchool.FinalTask3.domain.userRole.UserRole;
import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Data
public class RoleDTO {
    private String name;
    private Set<UserRole> roles;

    /**
     * Service class responsible for the interaction between the {@link RoleRepository} and the
     * {@link RoleController}. Obtains data from the
     * {@link RoleRepository} and returns the object(s) of the entity {@link RoleEntity} as
     * {@link RoleDTO} to the {@link RoleController}.
     */
    @Service
    @Transactional(readOnly = true)
    public static class RoleService extends AbstractServiceWithUpdate<RoleEntity, RoleDTO, String> {
        /**
         * All arguments constructor.
         * @param repository {@link RoleRepository} of the {@link RoleEntity} entity.
         * @param modelMapper ModelMapper that converts the {@link RoleEntity} to {@link RoleDTO}
         */
        public RoleService(RoleRepository repository, ModelMapper modelMapper) {
            super(repository, modelMapper);
        }

        /**
         * Returns the DTO class of the {@link RoleEntity} entity.
         * @return Returns the {@link RoleDTO} class.
         */
        @Override
        protected Class<RoleDTO> getDTOClass() {
            return RoleDTO.class;
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
}

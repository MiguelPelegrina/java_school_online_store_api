package com.javaSchool.FinalTask3.domain.user;

import com.javaSchool.FinalTask3.utils.AbstractServiceWithUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for the interaction between the {@link UserRepository} and the
 * {@link UserController}. Obtains data from the
 * {@link UserRepository} and returns the object(s) of the entity {@link UserEntity} as
 * {@link UserDTO} to the {@link UserController}.
 */
@Service
@Transactional(readOnly = true)
public class UserService extends AbstractServiceWithUpdate<UserEntity, UserDTO, Integer> {
    /**
     * All arguments constructor.
     * @param repository {@link UserRepository} of the {@link UserEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link UserEntity} to {@link UserDTO}
     */
    public UserService(UserRepository repository, ModelMapper modelMapper){
        super(repository, modelMapper);
    }

    /**
     * Returns the DTO class of the {@link UserEntity} entity.
     * @return Returns the {@link UserDTO} class.
     */
    @Override
    protected Class<UserDTO> getDTOClass() {
        return UserDTO.class;
    }

    @Override
    protected Integer getEntityId(UserEntity instance) {
        return instance.getId();
    }

    /**
     * Updates the values of an existing {@link UserEntity} instance with new ones.
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    @Override
    protected void updateValues(UserEntity existingInstance, UserEntity newInstance) {
        existingInstance.setName(newInstance.getName());
        existingInstance.setSurname(newInstance.getSurname());
        existingInstance.setDateOfBirth(newInstance.getDateOfBirth());
        existingInstance.setEmail(newInstance.getEmail());
        existingInstance.setPassword(newInstance.getPassword());
        existingInstance.setActive(newInstance.isActive());
        existingInstance.setPhoneNumber(newInstance.getPhoneNumber());
        existingInstance.setAddress(newInstance.getAddress());
        existingInstance.setRoles(newInstance.getRoles());
    }
}
